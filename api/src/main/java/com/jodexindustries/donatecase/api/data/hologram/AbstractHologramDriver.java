package com.jodexindustries.donatecase.api.data.hologram;

import com.jodexindustries.donatecase.api.data.storage.CaseLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHologramDriver implements HologramDriver {
    protected final Map<CaseLocation, Hologram> holograms = new HashMap<>();

    @Override
    public void remove(@NotNull CaseLocation block) {
        Hologram hologram = this.holograms.remove(block);
        if (hologram == null) return;

        hologram.delete();
    }

    @Override
    public void remove() {
        for (Hologram hologram : this.holograms.values()) hologram.delete();
        this.holograms.clear();
    }
}
