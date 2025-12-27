package com.jodexindustries.donatecase.api.manager;

import com.jodexindustries.donatecase.api.data.hologram.HologramDriver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HologramManager extends HologramDriver {

    default void register(@NotNull String name, @NotNull HologramDriver driver) {
        if (get().containsKey(name)) return;

        get().put(name, driver);
    }

    @Deprecated
    default void unregister(@NotNull String name) {
        get().remove(name);
    }

    @Deprecated
    default void unregister() {
        get().clear();
    }

    Map<String, HologramDriver> get();

    void set(@NotNull String name);

    void load();

}
