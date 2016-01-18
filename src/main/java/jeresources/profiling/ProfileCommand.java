package jeresources.profiling;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

public class ProfileCommand extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "profile";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "profile [chunks|stop]";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 1)
        {
            if (!Minecraft.getMinecraft().isSingleplayer())
                throw new WrongUsageException("can't use command in multiplayer");

            if ("stop".equals(args[0]) && !Profiler.stop())
                throw new WrongUsageException("Not profiling, run \"/profile [chunks]\" to start");

            int chunks;
            try
            {
                chunks = Integer.parseInt(args[0]);
            } catch (NumberFormatException e)
            {
                throw new WrongUsageException("[chunks] has to be a positive integer");
            }
            if (chunks <= 0) throw new WrongUsageException("[chunks] has to be a positive integer");
            if (!Profiler.init(sender, chunks))
                throw new WrongUsageException("Already profiling run \"/profile stop\" to stop");
        } else
        {
            throw new WrongUsageException(getCommandUsage(sender));
        }
    }
}
