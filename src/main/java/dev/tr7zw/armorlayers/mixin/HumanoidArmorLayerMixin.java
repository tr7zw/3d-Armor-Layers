package dev.tr7zw.armorlayers.mixin;

import com.mojang.blaze3d.vertex.*;
import dev.tr7zw.armorlayers.*;
import dev.tr7zw.skinlayers.accessor.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.entity.state.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.component.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> {

    @Inject(method = "renderArmorPiece", at = @At("HEAD"))
    private void renderArmorPiece(PoseStack poseStack, MultiBufferSource multiBufferSource, ItemStack itemStack,
            EquipmentSlot equipmentSlot, int i, A humanoidModel, CallbackInfo ci) {
        Equippable equippable = (Equippable) itemStack.get(DataComponents.EQUIPPABLE);
        if (equippable != null && HumanoidArmorLayer.shouldRender(itemStack, equipmentSlot)) {
            if (equipmentSlot == EquipmentSlot.CHEST) {
                ((ModelPartInjector) (Object) humanoidModel.body).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "BODY", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.BODY);
                ((ModelPartInjector) (Object) humanoidModel.leftArm).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "LEFT_ARM", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.LEFT_ARM);
                ((ModelPartInjector) (Object) humanoidModel.rightArm).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "RIGHT_ARM", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.RIGHT_ARM);
            }
            if (equipmentSlot == EquipmentSlot.HEAD) {
                ((ModelPartInjector) (Object) humanoidModel.head).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "HEAD", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.HEAD);
            }
            if (equipmentSlot == EquipmentSlot.FEET) {
                ((ModelPartInjector) (Object) humanoidModel.leftLeg).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "LEFT_SHOE", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.LEFT_SHOE);
                ((ModelPartInjector) (Object) humanoidModel.rightLeg).setInjectedMesh(ArmorModelUtil
                        .getMesh(equippable.assetId().get(), "RIGHT_SHOE", EquipmentClientInfo.LayerType.HUMANOID),
                        ArmorOffsetProvider.RIGHT_SHOE);
            }
            if (equipmentSlot == EquipmentSlot.LEGS) {
                ((ModelPartInjector) (Object) humanoidModel.body)
                        .setInjectedMesh(
                                ArmorModelUtil.getMesh(equippable.assetId().get(), "BODY_LEGS",
                                        EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS),
                                ArmorOffsetProvider.BODY_LEGS);
                ((ModelPartInjector) (Object) humanoidModel.leftLeg)
                        .setInjectedMesh(ArmorModelUtil.getMesh(equippable.assetId().get(), "LEFT_LEG",
                                EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS), ArmorOffsetProvider.LEFT_LEG);
                ((ModelPartInjector) (Object) humanoidModel.rightLeg)
                        .setInjectedMesh(
                                ArmorModelUtil.getMesh(equippable.assetId().get(), "RIGHT_LEG",
                                        EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS),
                                ArmorOffsetProvider.RIGHT_LEG);
            }
        }

    }

}
