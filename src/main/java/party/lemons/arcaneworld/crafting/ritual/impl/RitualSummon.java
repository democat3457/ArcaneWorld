package party.lemons.arcaneworld.crafting.ritual.impl;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import party.lemons.arcaneworld.crafting.ritual.Ritual;

import javax.annotation.Nonnull;

/**
 * Created by Sam on 12/09/2018.
 */
public class RitualSummon extends Ritual
{
    private final Class<? extends EntityLiving> entity;

    public RitualSummon(Class<? extends EntityLiving> entity, Ingredient... ing)
    {
        super(ing);

        this.entity = entity;
    }

    public void onActivate(@Nonnull World world, @Nonnull BlockPos pos)
    {
        try {
            EntityLiving mob = entity.getConstructor(World.class).newInstance(world);
            mob.onInitialSpawn(world.getDifficultyForLocation(pos), null);
            mob.setPosition(pos.getX() + 0.5F, pos.getY() + mob.height, pos.getZ() + 0.5F);
            world.spawnEntity(mob);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
