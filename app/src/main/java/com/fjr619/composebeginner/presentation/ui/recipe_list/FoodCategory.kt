package com.fjr619.composebeginner.presentation.ui.recipe_list

enum class FoodCategory(val value: String) {
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donuts"),
}

fun getAllFoodCategories(): List<FoodCategory> {
    return FoodCategory.values().asList()
}

fun getFoodCategory(value: String): FoodCategory? {
    return FoodCategory.values().find { it.value == value }
}