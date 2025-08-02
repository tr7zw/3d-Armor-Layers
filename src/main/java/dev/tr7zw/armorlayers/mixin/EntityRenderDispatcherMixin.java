package dev.tr7zw.armorlayers.mixin;

import dev.tr7zw.armorlayers.accessor.*;
import lombok.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.resources.model.*;
import org.spongepowered.asm.mixin.*;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin implements EntityRenderDispatcherAccessor {

    @Shadow
    private EquipmentAssetManager equipmentAssets;

    @Override
    public EquipmentAssetManager getEquipmentAssets() {
        return equipmentAssets;
    }
}
