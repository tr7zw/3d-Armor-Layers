package dev.tr7zw.armorlayers;

import dev.tr7zw.armorlayers.config.*;
import dev.tr7zw.armorlayers.versionless.*;
import dev.tr7zw.transition.loader.*;

public abstract class ArmorLayersModBase extends ModBase {

    public static ArmorLayersModBase instance;

    protected ArmorLayersModBase() {
        instance = this;
        ModLoaderUtil.disableDisplayTest();
        ModLoaderUtil.registerConfigScreen(ConfigScreenProvider::createConfigScreen);
    }

}
