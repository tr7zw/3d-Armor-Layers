//? if forge {
//package dev.tr7zw.armorlayers;
//
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.fml.DistExecutor;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import dev.tr7zw.transition.loader.ModLoaderUtil;
//
//@Mod("armorlayers3d")
//public class ArmorLayersBootstrap {
//
//	public ArmorLayersBootstrap(FMLJavaModLoadingContext context) {
//        ModLoaderUtil.setModLoadingContext(context);
//		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> new ArmorLayersMod().onInitialize());
//	}
//    public ArmorLayersBootstrap() {
//        this(FMLJavaModLoadingContext.get());
//    }
//	
//}
//? } else if neoforge {
//package dev.tr7zw.armorlayers;
//
//import net.neoforged.fml.common.Mod;
//import net.neoforged.fml.loading.FMLEnvironment;
//import dev.tr7zw.transition.loader.ModLoaderEventUtil;
//
//@Mod("armorlayers3d")
//public class ArmorLayersBootstrap {
//
//	public ArmorLayersBootstrap() {
//            if (FMLEnvironment.dist.isClient()){
//                    ModLoaderEventUtil.registerClientSetupListener(() -> new ArmorLayersMod().onInitialize());
//            }
//	}
//	
//}
//? }
