package me.ubmagh.commonapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier


abstract class BaseCommand<T> (
        @TargetAggregateIdentifier
        open val id : T
)

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

