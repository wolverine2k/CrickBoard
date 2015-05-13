/*
 *   CrickBoard Application
 *       Copyright (c) 2015 Naresh Mehta, All rights reserved.
 *       https://www.naresh.se/
 *
 *       This library is free software; you can redistribute it and/or
 *       modify it under the terms of the GNU Lesser General Public
 *       License as published by the Free Software Foundation; either
 *       version 3.0 of the License, or (at your option) any later version.
 *
 *       This library is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *       Lesser General Public License for more details.
 *
 *       You should have received a copy of the GNU Lesser General Public
 *       License along with this library.
 */
package se.naresh.com.crickboard;

public class MatchPlayers {
    /* 11 Main and 4 Substitutes who can be null */
    private static final Integer noOfPlayersPerMatch = 15;
    Player[] matchPlayers = null;
    private String UUID = null;
    /* TODO: Define Serial and DB management params in the class itself */

    MatchPlayers() {
        matchPlayers = new Player[noOfPlayersPerMatch];
    }
}
