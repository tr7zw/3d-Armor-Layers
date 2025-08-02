package dev.tr7zw.armorlayers.config;

import java.util.ArrayList;
import java.util.List;

import dev.tr7zw.armorlayers.*;
import dev.tr7zw.armorlayers.versionless.ModBase;
import dev.tr7zw.armorlayers.versionless.config.Config;
import dev.tr7zw.transition.mc.ComponentProvider;
import dev.tr7zw.trender.gui.client.AbstractConfigScreen;
import dev.tr7zw.trender.gui.client.BackgroundPainter;
import dev.tr7zw.trender.gui.widget.WButton;
import dev.tr7zw.trender.gui.widget.WGridPanel;
import dev.tr7zw.trender.gui.widget.WPlayerPreview;
import dev.tr7zw.trender.gui.widget.WTabPanel;
import dev.tr7zw.trender.gui.widget.data.Insets;
import dev.tr7zw.trender.gui.widget.icon.ItemIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.item.Items;

public class ConfigScreenProvider {

    public static Screen createConfigScreen(Screen parent) {
        return new CustomConfigScreen(parent).createScreen();
    }

    private static class CustomConfigScreen extends AbstractConfigScreen {

        public CustomConfigScreen(Screen previous) {
            super(ComponentProvider.translatable("text.armorlayers.title"), previous);

            WGridPanel root = new WGridPanel(8);
            root.setInsets(Insets.ROOT_PANEL);
            setRootPanel(root);

            WTabPanel wTabPanel = new WTabPanel();

            WGridPanel playerSettings = new WGridPanel();
            playerSettings.setInsets(new Insets(2, 4));

            // options page
            List<OptionInstance> options = new ArrayList<>();
            options.add(getOnOffOption("text.armorlayers.enable.hat", () -> ArmorLayersModBase.config.enableHat,
                    (b) -> ArmorLayersModBase.config.enableHat = b));
            options.add(getOnOffOption("text.armorlayers.enable.jacket", () -> ArmorLayersModBase.config.enableJacket,
                    (b) -> ArmorLayersModBase.config.enableJacket = b));
            options.add(getOnOffOption("text.armorlayers.enable.pants", () -> ArmorLayersModBase.config.enablePants,
                    (b) -> ArmorLayersModBase.config.enablePants = b));
            options.add(getOnOffOption("text.armorlayers.enable.shoes", () -> ArmorLayersModBase.config.enableShoes,
                    (b) -> ArmorLayersModBase.config.enableShoes = b));
            options.add(getIntOption("text.armorlayers.renderdistancelod", 5, 40,
                    () -> ArmorLayersModBase.config.renderDistanceLOD,
                    (i) -> ArmorLayersModBase.config.renderDistanceLOD = i));
            options.add(getDoubleOption("text.armorlayers.basevoxelsize", 1.101f, 1.5f, 0.001f,
                    () -> ArmorLayersModBase.config.baseVoxelSize, (i) -> {
                        ArmorLayersModBase.config.baseVoxelSize = (float) i;
                        ArmorModelUtil.reset();
                    }));
            options.add(getDoubleOption("text.armorlayers.headvoxelsize", 1.101f, 1.5f, 0.001f,
                    () -> ArmorLayersModBase.config.headVoxelSize, (i) -> {
                        ArmorLayersModBase.config.headVoxelSize = (float) i;
                        ArmorModelUtil.reset();
                    }));
            options.add(getDoubleOption("text.armorlayers.shoevoxelsize", 1.101f, 1.5f, 0.001f,
                    () -> (double) ArmorLayersModBase.config.shoeVoxelSize, (i) -> {
                        ArmorLayersModBase.config.shoeVoxelSize = (float) i;
                        ArmorModelUtil.reset();
                    }));
            options.add(getDoubleOption("text.armorlayers.pantstorsosize", 1.101f, 1.5f, 0.001f,
                    () -> (double) ArmorLayersModBase.config.pantsTorsoSize, (i) -> {
                        ArmorLayersModBase.config.pantsTorsoSize = (float) i;
                        ArmorModelUtil.reset();
                    }));

            var optionList = createOptionList(options);
            optionList.setGap(-1);
            //            optionList.setSize(14 * 20, 9 * 20);

            playerSettings.add(optionList, 0, 0, 12, 9);

            var playerPreview = new WPlayerPreview();
            playerPreview.setShowBackground(true);
            playerSettings.add(playerPreview, 13, 2);

            wTabPanel.add(playerSettings, b -> b.title(ComponentProvider.translatable("text.armorlayers.tab.armor"))
                    .icon(new ItemIcon(Items.VILLAGER_SPAWN_EGG)));

            WButton doneButton = new WButton(CommonComponents.GUI_DONE);
            doneButton.setOnClick(() -> {
                save();
                Minecraft.getInstance().setScreen(previous);
            });
            root.add(doneButton, 0, 26, 6, 2);

            wTabPanel.layout();
            root.add(wTabPanel, 0, 1);

            WButton resetButton = new WButton(ComponentProvider.translatable("controls.reset"));
            resetButton.setOnClick(() -> {
                reset();
                root.layout();
            });
            root.add(resetButton, 23, 26, 6, 2);

            root.setBackgroundPainter(BackgroundPainter.VANILLA);

            root.validate(this);
            root.setHost(this);
        }

        @Override
        public void reset() {
            ModBase.config = new Config();
        }

        @Override
        public void save() {
            ArmorLayersModBase.instance.writeConfig();
            ArmorModelUtil.reset();
        }

    }

}
