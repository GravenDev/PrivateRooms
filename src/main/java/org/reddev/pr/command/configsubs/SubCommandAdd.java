/*
 * Copyright (c)
 * RedDev 2020
 * Private Rooms Bot
 */

package org.reddev.pr.command.configsubs;

import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.reddev.pr.EmbedUtils;
import org.reddev.pr.References;
import org.reddev.pr.command.configsubs.utils.ConfigSubCommandExecutor;
import org.reddev.pr.utils.i18n.I18n;

import java.util.Map;

public class SubCommandAdd implements ConfigSubCommandExecutor {

    @Override
    public void execute(Server server, User user, ServerVoiceChannel voiceChannel, TextChannel textChannel, Map<String, ConfigSubCommandExecutor> subs, String[] args, MessageCreateEvent event) {

        if (event.getMessage().getMentionedUsers().size() != 1) {
            textChannel.sendMessage(EmbedUtils.getErrorEmbed(I18n.format(server.getId(), "command.config.add.error.no_mention"), server));
            return;
        }

        User mentionnedUser = event.getMessage().getMentionedUsers().get(0);

        voiceChannel.createUpdater().addPermissionOverwrite(mentionnedUser, Permissions.fromBitmask(References.USER_ALLOWED_PERMISSION_SHOWN, References.USER_DENIED_PERMISSION_SHOWN)).update();
        textChannel.sendMessage(EmbedUtils.getSuccessEmbed(I18n.format(server.getId(), "command.config.add.successful.title"), String.format(I18n.format(server.getId(), "command.config.add.successful.description"), mentionnedUser.getMentionTag())));

    }

    @Override
    public String getDescription(Server server) {
        return I18n.format(server.getId(), "command.config.add.description");
    }

    @Override
    public String getUsage() {
        return "<@mention>";
    }
}