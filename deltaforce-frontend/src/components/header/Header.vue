<template>
  <el-row :gutter=0 style="height: 50px">
    <el-col :span=10>
      <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
        <el-radio-button :value="false" @click="handleOpen">ex</el-radio-button>
        <el-radio-button :value="true" @click="handleClose">co</el-radio-button>
      </el-radio-group>
    </el-col>
    <el-col :span=4 style="display: flex;justify-content: center;align-items: center;"><el-avatar> user </el-avatar></el-col>
    <el-col :span=10></el-col>
  </el-row>
</template>
<script setup>
import { ref } from 'vue'

const spanData = ref({
  leftSpan: ref(4),
  rightSpan: ref(20),
  isCollapse: ref(false)
})
const handleOpen = () => {
  spanData.leftSpan = 4
  spanData.rightSpan= 20
  spanData.isCollapse = false
  this.$emit('handle', spanData)
}
const handleClose = () => {
  spanData.leftSpan = 2
  spanData.rightSpan= 22
  spanData.isCollapse = true
  this.$emit('handle', spanData)
}
</script>
<style scoped>

</style>