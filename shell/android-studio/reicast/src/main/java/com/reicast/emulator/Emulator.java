package com.reicast.emulator;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import com.reicast.emulator.emu.JNIdc;

public class Emulator extends Application {
    
    public static final String pref_nativeact = "enable_native";
    public static final String pref_dynarecopt = "dynarec_opt";
    public static final String pref_unstable = "unstable_opt";
    public static final String pref_dynsafemode = "dyn_safemode";
    public static final String pref_cable = "dc_cable";
    public static final String pref_dcregion = "dc_region";
    public static final String pref_broadcast = "dc_broadcast";
    public static final String pref_language = "dc_language";
    public static final String pref_limitfps = "limit_fps";
    public static final String pref_nosound = "sound_disabled";
    public static final String pref_nobatch = "nobatch";
    public static final String pref_interrupt = "delay_interrupt";
    public static final String pref_mipmaps = "use_mipmaps";
    public static final String pref_widescreen = "stretch_view";
    public static final String pref_frameskip = "frame_skip";
    public static final String pref_pvrrender = "pvr_render";
    public static final String pref_syncedrender = "synced_render";
    public static final String pref_modvols = "modifier_volumes";
    public static final String pref_bootdisk = "boot_disk";
    public static final String pref_usereios = "use_reios";
    public static final String pref_customtextures = "custom_textures";

    public static boolean dynarecopt = true;
    public static boolean idleskip = true;
    public static boolean unstableopt = false;
    public static boolean dynsafemode = false;
    public static int cable = 3;
    public static int dcregion = 3;
    public static int broadcast = 4;
    public static int language = 6;
    public static boolean limitfps = true;
    public static boolean nobatch = true;
    public static boolean nosound = false;
    public static boolean interrupt = false;
    public static boolean mipmaps = true;
    public static boolean widescreen = false;
    public static boolean subdivide = false;
    public static int frameskip = 0;
    public static boolean pvrrender = false;
    public static boolean syncedrender = false;
    public static boolean modvols = true;
    public static String bootdisk = null;
    public static boolean usereios = false;
    public static boolean nativeact = false;
    public static boolean customtextures = false;

    /**
     * Load the user configuration from preferences
     *
     */
    public void getConfigurationPrefs(SharedPreferences mPrefs) {
        Emulator.dynarecopt = mPrefs.getBoolean(pref_dynarecopt, dynarecopt);
        Emulator.unstableopt = mPrefs.getBoolean(pref_unstable, unstableopt);
        Emulator.dynsafemode = mPrefs.getBoolean(pref_dynsafemode, dynsafemode);
        Emulator.cable = mPrefs.getInt(pref_cable, cable);
        Emulator.dcregion = mPrefs.getInt(pref_dcregion, dcregion);
        Emulator.broadcast = mPrefs.getInt(pref_broadcast, broadcast);
        Emulator.language = mPrefs.getInt(pref_language, language);
        Emulator.limitfps = mPrefs.getBoolean(pref_limitfps, limitfps);
        Emulator.nosound = mPrefs.getBoolean(pref_nosound, nosound);
        Emulator.nobatch = mPrefs.getBoolean(pref_nobatch, nobatch);
        Emulator.mipmaps = mPrefs.getBoolean(pref_mipmaps, mipmaps);
        Emulator.widescreen = mPrefs.getBoolean(pref_widescreen, widescreen);
        Emulator.frameskip = mPrefs.getInt(pref_frameskip, frameskip);
        Emulator.pvrrender = mPrefs.getBoolean(pref_pvrrender, pvrrender);
        Emulator.syncedrender = mPrefs.getBoolean(pref_syncedrender, syncedrender);
        Emulator.bootdisk = mPrefs.getString(pref_bootdisk, bootdisk);
        Emulator.usereios = mPrefs.getBoolean(pref_usereios, usereios);
        Emulator.nativeact = mPrefs.getBoolean(pref_nativeact, nativeact);
        Emulator.customtextures = mPrefs.getBoolean(pref_customtextures, customtextures);
    }

    /**
     * Write configuration settings to the emulator
     *
     */
    public void loadConfigurationPrefs() {
        JNIdc.dynarec(Emulator.dynarecopt ? 1 : 0);
        JNIdc.idleskip(Emulator.idleskip ? 1 : 0);
        JNIdc.unstable(Emulator.unstableopt ? 1 : 0);
        JNIdc.safemode(Emulator.dynsafemode ? 1 : 0);
        JNIdc.cable(Emulator.cable);
        JNIdc.region(Emulator.dcregion);
        JNIdc.broadcast(Emulator.broadcast);
        JNIdc.language(Emulator.language);
        JNIdc.limitfps(Emulator.limitfps ? 1 : 0);
        JNIdc.nobatch(Emulator.nobatch ? 1 : 0);
        JNIdc.nosound(Emulator.nosound ? 1 : 0);
        JNIdc.mipmaps(Emulator.mipmaps ? 1 : 0);
        JNIdc.widescreen(Emulator.widescreen ? 1 : 0);
        JNIdc.subdivide(Emulator.subdivide ? 1 : 0);
        JNIdc.frameskip(Emulator.frameskip);
        JNIdc.pvrrender(Emulator.pvrrender ? 1 : 0);
        JNIdc.syncedrender(Emulator.syncedrender ? 1 : 0);
        JNIdc.modvols(Emulator.modvols ? 1 : 0);
        JNIdc.usereios(Emulator.usereios ? 1 : 0);
        JNIdc.bootdisk(Emulator.bootdisk);
        JNIdc.customtextures(Emulator.customtextures ? 1 : 0);
    }

    public void loadGameConfiguration(String gameId) {
        SharedPreferences mPrefs = getSharedPreferences(gameId, Activity.MODE_PRIVATE);
        JNIdc.dynarec(mPrefs.getBoolean(pref_dynarecopt, dynarecopt) ? 1 : 0);
        JNIdc.unstable(mPrefs.getBoolean(pref_unstable, unstableopt) ? 1 : 0);
        JNIdc.safemode(mPrefs.getBoolean(pref_dynsafemode, dynsafemode) ? 1 : 0);
        JNIdc.frameskip(mPrefs.getInt(pref_frameskip, frameskip));
        JNIdc.pvrrender(mPrefs.getBoolean(pref_pvrrender, pvrrender) ? 1 : 0);
        JNIdc.syncedrender(mPrefs.getBoolean(pref_syncedrender, syncedrender) ? 1 : 0);
        JNIdc.modvols(mPrefs.getBoolean(pref_modvols, modvols) ? 1 : 0);
        JNIdc.bootdisk(mPrefs.getString(pref_bootdisk, bootdisk));
        JNIdc.customtextures(mPrefs.getBoolean(pref_customtextures, customtextures) ? 1 : 0);
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
