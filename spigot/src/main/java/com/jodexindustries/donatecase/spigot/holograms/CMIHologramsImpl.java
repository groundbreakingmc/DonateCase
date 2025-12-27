package com.jodexindustries.donatecase.spigot.holograms;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import com.jodexindustries.donatecase.api.data.casedefinition.CaseSettings;
import com.jodexindustries.donatecase.api.data.hologram.AbstractHologramDriver;
import com.jodexindustries.donatecase.api.data.storage.CaseLocation;
import com.jodexindustries.donatecase.spigot.tools.BukkitUtils;
import net.Zrips.CMILib.Container.CMILocation;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Class for CMI Holograms implementation
 */
public class CMIHologramsImpl extends AbstractHologramDriver {

    @Override
    public void forceCreate(@NotNull CaseLocation block, CaseSettings.@NotNull Hologram caseHologram) {
        double height = caseHologram.height();

        CMILocation location = new CMILocation(BukkitUtils.toBukkit(block).add(0.5, height, 0.5));

        CMIHologram hologram = new CMIHologram("DonateCase-" + UUID.randomUUID(), location);

        hologram.setLines(caseHologram.message());

        hologram.setShowRange(caseHologram.range());

        CMI.getInstance().getHologramManager().addHologram(hologram);

        hologram.update();

        this.holograms.put(block, hologram::remove);
    }

}
