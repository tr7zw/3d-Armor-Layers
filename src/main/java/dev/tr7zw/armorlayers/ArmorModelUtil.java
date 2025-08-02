package dev.tr7zw.armorlayers;

import com.google.common.cache.*;
import com.mojang.blaze3d.platform.*;
import dev.tr7zw.armorlayers.accessor.*;
import dev.tr7zw.skinlayers.*;
import dev.tr7zw.skinlayers.api.*;
import net.fabricmc.api.*;
import net.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ArmorModelUtil {

    private static Cache<AbstractTexture, NativeImage> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(60L, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<AbstractTexture, NativeImage>() {

                @Override
                public void onRemoval(RemovalNotification<AbstractTexture, NativeImage> notification) {
                    try {
                        notification.getValue().close();
                    } catch (Exception ex) {
                        ArmorLayersModBase.LOGGER.error("Error while closing a texture.", ex);
                    }
                }
            }).build();
    private static Function<LayerTextureKey, ResourceLocation> layerTextureLookup = Util
            .memoize((layerTextureKey) -> layerTextureKey.layer.getTextureLocation(layerTextureKey.layerType));
    private static Map<ResourceKey, Map<String, Mesh>> meshMap = new HashMap<>();

    @Environment(EnvType.CLIENT)
    static record LayerTextureKey(EquipmentClientInfo.LayerType layerType, EquipmentClientInfo.Layer layer) {
    }

    public static Mesh getMesh(ResourceKey key, String slot, EquipmentClientInfo.LayerType layerType) {
        var typeMap = meshMap.computeIfAbsent(key, k -> new HashMap<>());
        List<EquipmentClientInfo.Layer> list = ((EntityRenderDispatcherAccessor) (Object) Minecraft.getInstance()
                .getEntityRenderDispatcher()).getEquipmentAssets().get(key).getLayers(layerType);
        return typeMap.computeIfAbsent(slot, s -> {
            var textureId = layerTextureLookup.apply(new LayerTextureKey(layerType, list.get(0)));
            var texture = SkinUtil.getTexture(textureId, null);
            if (s.equals("HEAD")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 8, 8, 8, 0, 0, false, 0.6f, false);
            }
            if (s.equals("BODY")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 8, 12, 4, 16, 16, true, 0, false);
            }
            if (s.equals("BODY_LEGS")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 8, 12, 4, 16, 16, true, 0, false);
            }
            if (s.equals("LEFT_ARM")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 40, 16, true, -2f, true);
            }
            if (s.equals("RIGHT_ARM")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 40, 16, true, -2f, false);
            }
            if (s.equals("LEFT_SHOE")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 0, 16, true, 0, true);
            }
            if (s.equals("RIGHT_SHOE")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 0, 16, true, 0, false);
            }
            if (s.equals("LEFT_LEG")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 0, 16, true, 0, true);
            }
            if (s.equals("RIGHT_LEG")) {
                return SkinLayersAPI.getMeshHelper().create3DMesh(texture, 4, 12, 4, 0, 16, true, 0, false);
            }
            return null;
        });
    }

    public static void reset() {
        meshMap.clear();
    }

}
