/***************************************************************************
 *                 (C) Copyright 2003-2014 - Faiumoni e.V.                 *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.tools;

import games.stendhal.common.grammar.Grammar;
import games.stendhal.server.core.config.CreatureGroupsXMLLoader;
import games.stendhal.server.core.config.ItemGroupsXMLLoader;
import games.stendhal.server.core.rule.defaultruleset.DefaultCreature;
import games.stendhal.server.core.rule.defaultruleset.DefaultItem;

import java.net.URI;
import java.util.List;

/**
 * Print creature and item descriptions in a format usable for translations.
 */
public class GenerateDescriptions {
	private static void creatureDescriptions() throws Exception {
		final CreatureGroupsXMLLoader loader = new CreatureGroupsXMLLoader("/data/conf/creatures.xml");
		final List<DefaultCreature> creatures = loader.load();

		for (final DefaultCreature creature : creatures) {
			System.out.println(creature.getCreatureName() + "=" + creature.getCreatureName());
			System.out.println(describe(creature.getDescription(), creature.getCreatureName()));
		}
	}

	private static void itemDescriptions() throws Exception {
		final ItemGroupsXMLLoader loader = new ItemGroupsXMLLoader(new URI(
				"/data/conf/items.xml"));
		final List<DefaultItem> items = loader.load();

		for (final DefaultItem item : items) {
			System.out.println(item.getItemName() + "=" + item.getItemName());
			System.out.println(describe(item.getDescription(), item.getItemName()));
		}
	}
	
	private static String describe(String desc, String name) {
		if (desc == null || desc.trim().isEmpty()) {
			// The default description
			desc = "You see " + Grammar.a_noun(name) + ".";
		}
		return desc + "=" + desc;
	}

	public static void main(final String[] args) throws Exception {
		itemDescriptions();
		creatureDescriptions();
	}
}