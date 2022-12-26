package me.ubmagh.commonapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier



data class CreateRadarCommand(
        override val id : String,
        val latitude : Float,
        val longitude : Float,
        val vitesse_max : Float,
):BaseCommand<String>(id)


data class UpdateRadarCommand(
        override val id : String,
        val latitude : Float,
        val longitude : Float,
        val vitesse_max : Float,
):BaseCommand<String>(id)


data class DeleteRadarCommand(
        override val id : String,
):BaseCommand<String>(id)

