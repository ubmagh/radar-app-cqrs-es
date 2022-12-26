package me.ubmagh.commonapi.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

abstract class BaseCommand<T> (
        @TargetAggregateIdentifier
        open val id : T
)