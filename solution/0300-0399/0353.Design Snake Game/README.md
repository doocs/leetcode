# [353. 贪吃蛇](https://leetcode.cn/problems/design-snake-game)

[English Version](/solution/0300-0399/0353.Design%20Snake%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你设计一个 <a href="https://baike.baidu.com/item/%E8%B4%AA%E5%90%83%E8%9B%87/9510203?fr=aladdin" target="_blank">贪吃蛇游戏</a>，该游戏将会在一个 <strong>屏幕尺寸 = 宽度 x 高度 </strong>的屏幕上运行。如果你不熟悉这个游戏，可以 <a href="http://patorjk.com/games/snake/">点击这里</a> 在线试玩。</p>

<p>起初时，蛇在左上角的 <code>(0, 0)</code><strong> </strong>位置，身体长度为 <code>1</code> 个单位。</p>

<p>你将会被给出一个数组形式的食物位置序列 <code>food</code> ，其中 <code>food[i] = (r<sub>i</sub>, c<sub>i</sub>)</code> 。当蛇吃到食物时，身子的长度会增加 <code>1</code> 个单位，得分也会 <code>+1</code> 。</p>

<p>食物不会同时出现，会按列表的顺序逐一显示在屏幕上。比方讲，第一个食物被蛇吃掉后，第二个食物才会出现。</p>

<p>当一个食物在屏幕上出现时，保证 <strong>不会</strong> 出现在被蛇身体占据的格子里。</p>

<p class="MachineTrans-lang-zh-CN">如果蛇越界（与边界相撞）或者头与 <strong>移动后</strong> 的身体相撞（即，身长为 <code>4</code> 的蛇无法与自己相撞），游戏结束。</p>

<p>实现 <code>SnakeGame</code> 类：</p>

<ul>
	<li><code>SnakeGame(int width, int height, int[][] food)</code> 初始化对象，屏幕大小为 <code>height x width</code> ，食物位置序列为 <code>food</code></li>
	<li><code>int move(String direction)</code> 返回蛇在方向 <code>direction</code> 上移动后的得分。如果游戏结束，返回 <code>-1</code> 。</li>
</ul>
 

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0353.Design%20Snake%20Game/images/snake.jpg" style="width: 800px; height: 302px;" />
<pre>
<strong>输入：</strong>
["SnakeGame", "move", "move", "move", "move", "move", "move"]
[[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
<strong>输出：</strong>
[null, 0, 0, 1, 1, 2, -1]

<strong>解释：</strong>
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move("R"); // 返回 0
snakeGame.move("D"); // 返回 0
snakeGame.move("R"); // 返回 1 ，蛇吃掉了第一个食物，同时第二个食物出现在 (0, 1)
snakeGame.move("U"); // 返回 1
snakeGame.move("L"); // 返回 2 ，蛇吃掉了第二个食物，没有出现更多食物
snakeGame.move("U"); // 返回 -1 ，蛇与边界相撞，游戏结束

</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= width, height <= 10<sup>4</sup></code></li>
	<li><code>1 <= food.length <= 50</code></li>
	<li><code>food[i].length == 2</code></li>
	<li><code>0 <= r<sub>i</sub> < height</code></li>
	<li><code>0 <= c<sub>i</sub> < width</code></li>
	<li><code>direction.length == 1</code></li>
	<li><code>direction</code> is <code>'U'</code>, <code>'D'</code>, <code>'L'</code>, or <code>'R'</code>.</li>
	<li>最多调用 <code>10<sup>4</sup></code> 次 <code>move</code> 方法</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
