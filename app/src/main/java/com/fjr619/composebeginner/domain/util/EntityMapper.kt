package com.fjr619.composebeginner.domain.util

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel):Entity
}