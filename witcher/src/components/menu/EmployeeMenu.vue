<template>
    <div>
        <md-list-item :to="'receips'">
            <md-icon >receipt</md-icon>
            <span class="md-list-item-text">Рецепты</span>
        </md-list-item>

        <md-list-item :to="'grass'">
            <md-icon>spa</md-icon>
            <span class="md-list-item-text">Травы</span>
        </md-list-item>

        <md-list-item>
            <md-icon>adb</md-icon>
            <span class="md-list-item-text">Животные</span>
        </md-list-item>

        <md-list-item>
            <md-icon>photo_filter</md-icon>
            <span class="md-list-item-text">Руны</span>
        </md-list-item>
    </div>
</template>

<script>
export default {
    name: 'EmployeeMenu'
}
</script>