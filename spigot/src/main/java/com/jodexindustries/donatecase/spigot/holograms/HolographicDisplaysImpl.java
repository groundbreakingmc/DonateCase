package com.jodexindustries.donatecase.spigot.holograms;

import com.jodexindustries.donatecase.api.DCAPI;
import com.jodexindustries.donatecase.api.data.casedefinition.CaseSettings;
import com.jodexindustries.donatecase.api.data.hologram.AbstractHologramDriver;
import com.jodexindustries.donatecase.api.data.storage.CaseLocation;
import com.jodexindustries.donatecase.api.tools.DCTools;
import com.jodexindustries.donatecase.spigot.BukkitBackend;
import com.jodexindustries.donatecase.spigot.tools.BukkitUtils;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.PlaceholderSetting;
import org.jetbrains.annotations.NotNull;

/**
 * Class for HolographicDisplays Holograms implementation
 */
public class HolographicDisplaysImpl extends AbstractHologramDriver {

    @NotNull
    private final HolographicDisplaysAPI api = HolographicDisplaysAPI.get(((BukkitBackend) DCAPI.getInstance().getPlatform()).getPlugin());

    @Override
    public void forceCreate(@NotNull CaseLocation block, CaseSettings.@NotNull Hologram caseHologram) {
        if (this.holograms.containsKey(block)) return;

        double height = caseHologram.height();

        Hologram hologram = this.api.createHologram(BukkitUtils.toBukkit(block).add(.5, height, .5));
        hologram.setPlaceholderSetting(PlaceholderSetting.DEFAULT);
        caseHologram.message().forEach(line -> hologram.getLines().appendText(
                DCTools.rc((line))
        ));

        this.holograms.put(block, hologram::delete);
    }
}
