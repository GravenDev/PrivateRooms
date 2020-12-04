package org.reddev.privateroomsreborn.commands.settings.subs

import kong.unirest.Unirest
import org.hjson.JsonValue
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.entity.server.Server
import org.javacord.api.event.message.MessageCreateEvent
import org.reddev.privateroomsreborn.api.commands.CommandDescriptor
import org.reddev.privateroomsreborn.api.commands.ICommand
import org.reddev.privateroomsreborn.utils.BotConfig
import org.reddev.privateroomsreborn.utils.ServerConfig
import org.reddev.privateroomsreborn.utils.general.ConfigUtils
import org.reddev.privateroomsreborn.utils.general.ListUtils
import org.reddev.privateroomsreborn.utils.general.UnirestUtils

import java.awt.*

import static org.reddev.privateroomsreborn.utils.general.LangUtils.l
import static org.reddev.privateroomsreborn.utils.general.StringUtils.j

class SSubLanguage implements ICommand {

    @Override
    void execute(MessageCreateEvent event, BotConfig config, String cmd, String[] args) {
        ServerConfig sConfig = ConfigUtils.getServerConfig(event.server.get())
        if (args.length != 1) {
            event.channel.sendMessage(
                    new EmbedBuilder()
                            .setTitle(l("errors.incorrect-syntax", event.server.get()))
                            .setColor(Color.RED)
                            .addField(
                                    j("``%s%s %s``",
                                            sConfig.getCustomPrefix(config),
                                            cmd,
                                            getDescriptor(event.server.get()).usage), "** **")
            )
            return
        }


        String language = args[0]

        if (!ListUtils.contains(config.languages, JsonValue.valueOf(language))) {
            event.channel.sendMessage(new EmbedBuilder()
                    .setTitle(j("%s !", l("errors.error", event.server.get())))
                    .setDescription(l("cmd.settings.language.error.language-does-not-exists.desc", event.server.get()))
                    .addField(l("cmd.settings.language.error.language-does-not-exists.possibility", event.server.get()), j("`%s`", config.languages.join("`, `").replace("\"", "")))
                    .setColor(Color.RED)
            )
            return
        }
        sConfig.setLanguage(language)
        ConfigUtils.update(event.server.get(), sConfig)
        event.channel.sendMessage(new EmbedBuilder()
                .setTitle(l("cmd.settings.language.title", event.server.get()))
                .setDescription(j("%s `%s`",
                        l("cmd.settings.language.desc", event.server.get()),
                        UnirestUtils.getCountryName(language).capitalize()
                ))
                .setThumbnail(UnirestUtils.getCountryFlag(language))
                .setColor(Color.GREEN)
        )
    }

    @Override
    CommandDescriptor getDescriptor(Server guild) {
        return new CommandDescriptor(usage: "<lang>", description: l("cmd.settings.language.cmd-desc", guild))
    }

}