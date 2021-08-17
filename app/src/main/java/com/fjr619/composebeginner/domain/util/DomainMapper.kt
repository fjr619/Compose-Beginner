package com.fjr619.composebeginner.domain.util

interface DomainMapper<T, DomainModel> {

    //dari network ke domain untuk lanjut ke UI
    fun mapToDomainModel(model: T): DomainModel

    //dari domain ke network, misal untuk kebutuhan request network
    fun mapFromDomainModel(domainModel: DomainModel):T
}