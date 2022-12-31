/*
 * PrivateRooms is a discord bot to manage vocal chats.
 * Copyright (C) 2022 GravenDev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.redstom.privaterooms.util.room;

import me.redstom.privaterooms.util.permission.PermissionSet;
import net.dv8tion.jda.api.Permission;

import java.util.EnumSet;

public enum RoomVisibility implements PermissionSet {
    PUBLIC(
      NONE,
      NONE,
      "\uD83D\uDD13"), // "🔓"
    PRIVATE(
      NONE,
      EnumSet.of(Permission.VOICE_CONNECT),
      "\uD83D\uDD10"), // "🔐"
    HIDDEN(
      NONE,
      EnumSet.of(Permission.VOICE_CONNECT, Permission.VIEW_CHANNEL),
      "\uD83D\uDD12"); // "🔒"

    private EnumSet<Permission> allow;
    private EnumSet<Permission> deny;
    private long allowRaw;
    private long denyRaw;

    private String emoji;

    RoomVisibility(EnumSet<Permission> allow, EnumSet<Permission> deny, String emoji) {
        this.allow = allow;
        this.deny = deny;
        this.allowRaw = Permission.getRaw(allow);
        this.denyRaw = Permission.getRaw(deny);
        this.emoji = emoji;
    }

    @Override
    public EnumSet<Permission> allowList() {
        return allow;
    }

    @Override
    public EnumSet<Permission> denyList() {
        return deny;
    }

    @Override
    public long allowRaw() {
        return allowRaw;
    }

    @Override
    public long denyRaw() {
        return denyRaw;
    }

    public String emoji() {
        return emoji;
    }
}