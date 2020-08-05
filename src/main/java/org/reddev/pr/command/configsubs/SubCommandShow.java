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

public class SubCommandShow implements ConfigSubCommandExecutor {

    @Override
    public void execute(Server server, User user, ServerVoiceChannel voiceChannel, TextChannel textChannel, Map<String, ConfigSubCommandExecutor> subs, String[] args, MessageCreateEvent event) {

        voiceChannel.createUpdater().addPermissionOverwrite(server.getEveryoneRole(), Permissions.fromBitmask(References.USER_ALLOWED_PERMISSION_SHOWN, References.USER_DENIED_PERMISSION_SHOWN)).update();
        textChannel.sendMessage(EmbedUtils.getSuccessEmbed(I18n.format(server.getId(), "command.config.show.successful.title"), I18n.format(server.getId(), "command.config.show.successful.description")));

    }

    @Override
    public String getDescription(Server server) {
        return I18n.format(server.getId(), "command.config.show.description");
    }
}