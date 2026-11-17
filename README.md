# Twilit Gourmet — Minecraft 26.1.2 NeoForge

移植来源：用户提供的 `twilit-gourmet-main.zip`，原作者 GizmoTheMoonPig、MemeDream / TeamTwilight。
模组 ID 保持 `twilitgourmet`，原有内容与注册 ID 保留，授权为 MIT，见 LICENSE。

## 环境与前置

- Minecraft 26.1.2
- NeoForge 26.1.2.126
- Java 25
- Farmer's Delight 1.3.3-fix8（用户提供的 26.1.2 JAR）
- The Twilight Forest 4.9.3912（用户提供的 26.1.2 JAR）
- JEI 29.53.0.115 为可选兼容，开发运行已配置本地 JAR。

所给农夫乐事和暮色森林 JAR 已复制到 `libs`，只用于开发依赖，不会打入产物。
原工程的 EMI、Jade、Advanced Core Info 和 Advanced Loot Info 开发依赖已移出构建。
原 1.21.1 EMI 适配源码保留在 `legacy/compat`；本次适配现有整合包中的 JEI。

## 已适配

- 物品、方块注册时传入新版资源 ID。
- 工具材料、刀具伤害逻辑、护甲属性和蟹壳头盔装备资产。
- 快速进食、食物状态效果、饮用动画与碗瓶等使用余物。
- 方块交互、邻居更新、树汁提取与方块实体保存及同步。
- 树汁的新版物品和方块染色接口。
- 蟹壳头盔使用暮色森林的新版模型接口和头部绑定，保留钳腿动画。
- 奶酪熟成及腐败、篝火烟熏、炽铁刀具自动烹熟砧板产物。
- JEI 配方隐藏接口；配方、战利品修改器和新版物品模型定义。

运行资源包含 `src/main/resources` 和 `src/generated/resources`。
26.1.2 数据生成器位于 `src/data/java`，使用独立的 Gradle `data` 源集，不会打入运行 JAR。
原 1.21.1 数据生成源码保留在 `legacy/datagen-1.21.1` 供参考。
客户端沿用原作的 `BlockStateGenerator`、`ItemModelGenerator`、`LangGenerator` 和 `SoundGenerator` 分工及逐项注册结构，模型与方块状态使用 26.1.2 的 `ModelProvider`、`ModelTemplate` 和 `PropertyDispatch` 接口生成。

## 数据生成

```powershell
./gradlew runData
```

生成配方及解锁进度、战利品表、战利品修改器、标签、数据映射、世界生成和树汁注册数据，以及模型、方块状态、新版物品定义、英文语言和音效定义。
输出目录为 `src/generated/resources`，生成结果已包含在正常构建资源中。

`./gradlew runClientData` 是完整生成任务的别名。
`./gradlew runServerData` 只更新服务端数据，使用 `--uncached` 保留现有客户端资源；删除旧数据后需要清理过期结果时，执行完整的 `runData`。
手工资源及中文翻译继续放在 `src/main/resources`，数据生成缓存 `.cache` 不会打包。

## 构建

使用 Java 25，在本目录执行：

```powershell
./gradlew build
```

输出文件位于 `build/libs`。

已执行数据生成及 `./gradlew build --console=plain`，生成、编译与打包通过；未启动游戏测试。
