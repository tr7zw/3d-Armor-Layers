package dev.tr7zw.armorlayers;

import dev.tr7zw.skinlayers.SkinLayersModBase;
import dev.tr7zw.skinlayers.api.*;

public class ArmorOffsetProvider {
    public static final OffsetProvider HEAD = createVanilla(Shape.HEAD);
    public static final OffsetProvider RIGHT_ARM = createVanilla(Shape.ARMS, true);
    public static final OffsetProvider BODY = createVanilla(Shape.BODY);
    public static final OffsetProvider LEFT_ARM = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.baseVoxelSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.baseVoxelSize;

        float x = 0.0F;
        float y = Shape.ARMS.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };
    public static final OffsetProvider RIGHT_LEG = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.baseVoxelSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.baseVoxelSize;

        float x = 0.0F;
        float y = Shape.LEGS.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };
    public static final OffsetProvider LEFT_LEG = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.baseVoxelSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.baseVoxelSize;

        float x = -0.998F;
        float y = Shape.LEGS.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };
    public static final OffsetProvider RIGHT_SHOE = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.shoeVoxelSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.shoeVoxelSize;

        float x = 0.0F;
        float y = Shape.LEGS.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };
    public static final OffsetProvider LEFT_SHOE = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.shoeVoxelSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.shoeVoxelSize;

        float x = -0.998F;
        float y = Shape.LEGS.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };
    public static final OffsetProvider BODY_LEGS = (stack, mesh) -> {
        float pixelScaling = ArmorLayersMod.config.pantsTorsoSize;
        float heightScaling = 1.035F;
        float widthScaling = ArmorLayersMod.config.pantsTorsoSize;

        float x = 0.0F;
        float y = Shape.BODY.yOffsetMagicValue();

        stack.scale(widthScaling, heightScaling, pixelScaling);

        mesh.setPosition(x, y, 0.0F);
    };

    private static OffsetProvider createVanilla(Shape shape) {
        return createVanilla(shape, false);
    }

    private static OffsetProvider createVanilla(Shape shape, boolean mirrored) {
        return (stack, mesh) -> {
            float pixelScaling = ArmorLayersMod.config.baseVoxelSize;
            float heightScaling = 1.035F;
            float widthScaling = ArmorLayersMod.config.baseVoxelSize;

            float x = 0.0F;
            float y = 0.0F;
            if (shape == Shape.ARMS) {
                x = 0.998F;
            } else if (shape == Shape.ARMS_SLIM) {
                x = 0.499F;
            }

            if (mirrored) {
                x *= -1.0F;
            }

            if (shape == Shape.HEAD) {
                float voxelSize = ArmorLayersMod.config.headVoxelSize;

                stack.translate((double) 0.0F, (double) -0.25F, (double) 0.0F);
                stack.scale(voxelSize, voxelSize, voxelSize);
                stack.translate((double) 0.0F, (double) 0.25F, (double) 0.0F);
                stack.translate((double) 0.0F, -0.04, (double) 0.0F);
            } else {
                stack.scale(widthScaling, heightScaling, pixelScaling);
                y = shape.yOffsetMagicValue();
            }

            mesh.setPosition(x, y, 0.0F);
        };
    }
}
