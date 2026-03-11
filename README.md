# About the mod

This mod makes combat harder by applying the **Inhibited** effect (from the required dependency mod **[Inhibited](https://www.curseforge.com/minecraft/mc-mods/inhibited)**) to players while they are considered **“in combat”**.

The **Inhibited** effect prevents **placing** and **breaking** blocks.

“In combat” is defined by configurable modules. By default, combat is detected when the player **deals damage** or **takes damage** involving hostile entities. Combat can also be **maintained** while the player remains near hostile entities.

## What is a “hostile entity”?

“Hostile” is configurable. An entity can be treated as hostile using an **OR** rule:

- Any entity (`includeAll`)
- Entities that implement `IMob` (common Forge heuristic for hostile mobs)
- Entities that are currently targeting a player (attack target is a player)

You can also override entity classification using:

- `allowList`: entity IDs that always match (override)
- `excludeList`: entity IDs that never match

Entity IDs use the registry format: `modid:entity_name` (example: `minecraft:zombie`).

Because Minecraft 1.12.2 does not provide a universal, reliable way to classify modded entities as “hostile”, these options exist to let modpack creators define behavior explicitly. Suggestions for better heuristics are welcome.

# Modules

Each module can be enabled/disabled in the config.

## DealingDamageModule

Applies **Inhibited** to the player when they **deal damage**, if the damaged entity matches the configured hostile rules and filters.

## TakingDamageModule

Applies **Inhibited** to the player when they **take damage**, if the attacker entity matches the configured hostile rules and filters.

Optionally, it can also trigger on **non-entity** damage sources (fall, lava, cactus, etc.) depending on config.

## NearEnemyModule

Scans for nearby entities matching the configured hostile rules and filters. It has two modes:

- `APPLY_EFFECT`: applies **Inhibited** while near a matching entity.
- `PREVENT_EXPIRING`: does not apply **Inhibited** by itself, but refreshes the effect if the player already has it and it is about to expire.

A maximum number of reapplications can also be configured.

Default: `PREVENT_EXPIRING`

## NearBossModule

Applies **Inhibited** while the player is near an entity in a configured boss whitelist (`bossList`).

Boss detection is whitelist-based by design (no automatic boss detection).

Default: disabled
