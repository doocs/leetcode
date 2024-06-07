---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0353.Design%20Snake%20Game/README.md
tags:
    - 设计
    - 队列
    - 数组
    - 哈希表
    - 模拟
---

<!-- problem:start -->

# [353. 贪吃蛇 🔒](https://leetcode.cn/problems/design-snake-game)

[English Version](/solution/0300-0399/0353.Design%20Snake%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双端队列模拟

我们可以使用双端队列来模拟蛇的移动。

定义一个双端队列 $q$，其中保存蛇的身体坐标，队头为蛇头，队尾为蛇尾。同时使用一个集合 $vis$ 来保存蛇的身体坐标，用于快速判断蛇头是否与蛇身相撞。

定义一个变量 $score$ 来保存蛇的得分，初始值为 $0$；定义一个变量 $idx$ 来保存当前食物的索引，初始值为 $0$。

每次移动时，首先判断蛇头是否与边界相撞，如果相撞则游戏结束，返回 $-1$；否则，判断蛇头是否与食物重合，如果重合则蛇的得分加 $1$，同时食物索引 $idx$ 加 $1$；否则，蛇的身体长度不变，需要将蛇尾从队尾弹出，并从集合 $vis$ 中删除对应的坐标。

然后，判断蛇头是否与蛇身相撞，如果相撞则游戏结束，返回 $-1$；否则，将蛇头的坐标加入集合 $vis$ 中，并从队头加入蛇头的坐标。

最后，返回蛇的得分 $score$。

时间复杂度 $O(k)$，空间复杂度 $O(k)$，其中 $k$ 为移动的次数。

<!-- tabs:start -->

#### Python3

```python
class SnakeGame:
    def __init__(self, width: int, height: int, food: List[List[int]]):
        self.m = height
        self.n = width
        self.food = food
        self.score = 0
        self.idx = 0
        self.q = deque([(0, 0)])
        self.vis = {(0, 0)}

    def move(self, direction: str) -> int:
        i, j = self.q[0]
        x, y = i, j
        match direction:
            case "U":
                x -= 1
            case "D":
                x += 1
            case "L":
                y -= 1
            case "R":
                y += 1
        if x < 0 or x >= self.m or y < 0 or y >= self.n:
            return -1
        if (
            self.idx < len(self.food)
            and x == self.food[self.idx][0]
            and y == self.food[self.idx][1]
        ):
            self.score += 1
            self.idx += 1
        else:
            self.vis.remove(self.q.pop())
        if (x, y) in self.vis:
            return -1
        self.q.appendleft((x, y))
        self.vis.add((x, y))
        return self.score


# Your SnakeGame object will be instantiated and called as such:
# obj = SnakeGame(width, height, food)
# param_1 = obj.move(direction)
```

#### Java

```java
class SnakeGame {
    private int m;
    private int n;
    private int[][] food;
    private int score;
    private int idx;
    private Deque<Integer> q = new ArrayDeque<>();
    private Set<Integer> vis = new HashSet<>();

    public SnakeGame(int width, int height, int[][] food) {
        m = height;
        n = width;
        this.food = food;
        q.offer(0);
        vis.add(0);
    }

    public int move(String direction) {
        int p = q.peekFirst();
        int i = p / n, j = p % n;
        int x = i, y = j;
        if ("U".equals(direction)) {
            --x;
        } else if ("D".equals(direction)) {
            ++x;
        } else if ("L".equals(direction)) {
            --y;
        } else {
            ++y;
        }
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return -1;
        }
        if (idx < food.length && x == food[idx][0] && y == food[idx][1]) {
            ++score;
            ++idx;
        } else {
            int t = q.pollLast();
            vis.remove(t);
        }
        int cur = f(x, y);
        if (vis.contains(cur)) {
            return -1;
        }
        q.offerFirst(cur);
        vis.add(cur);
        return score;
    }

    private int f(int i, int j) {
        return i * n + j;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
```

#### C++

```cpp
class SnakeGame {
public:
    SnakeGame(int width, int height, vector<vector<int>>& food) {
        m = height;
        n = width;
        this->food = food;
        score = 0;
        idx = 0;
        q.push_back(0);
        vis.insert(0);
    }

    int move(string direction) {
        int p = q.front();
        int i = p / n, j = p % n;
        int x = i, y = j;
        if (direction == "U") {
            --x;
        } else if (direction == "D") {
            ++x;
        } else if (direction == "L") {
            --y;
        } else {
            ++y;
        }
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return -1;
        }
        if (idx < food.size() && x == food[idx][0] && y == food[idx][1]) {
            ++score;
            ++idx;
        } else {
            int tail = q.back();
            q.pop_back();
            vis.erase(tail);
        }
        int cur = f(x, y);
        if (vis.count(cur)) {
            return -1;
        }
        q.push_front(cur);
        vis.insert(cur);
        return score;
    }

private:
    int m;
    int n;
    vector<vector<int>> food;
    int score;
    int idx;
    deque<int> q;
    unordered_set<int> vis;

    int f(int i, int j) {
        return i * n + j;
    }
};

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame* obj = new SnakeGame(width, height, food);
 * int param_1 = obj->move(direction);
 */
```

#### Go

```go
type SnakeGame struct {
	m     int
	n     int
	food  [][]int
	score int
	idx   int
	q     []int
	vis   map[int]bool
}

func Constructor(width int, height int, food [][]int) SnakeGame {
	return SnakeGame{height, width, food, 0, 0, []int{0}, map[int]bool{}}
}

func (this *SnakeGame) Move(direction string) int {
	f := func(i, j int) int {
		return i*this.n + j
	}
	p := this.q[0]
	i, j := p/this.n, p%this.n
	x, y := i, j
	if direction == "U" {
		x--
	} else if direction == "D" {
		x++
	} else if direction == "L" {
		y--
	} else {
		y++
	}
	if x < 0 || x >= this.m || y < 0 || y >= this.n {
		return -1
	}
	if this.idx < len(this.food) && x == this.food[this.idx][0] && y == this.food[this.idx][1] {
		this.score++
		this.idx++
	} else {
		t := this.q[len(this.q)-1]
		this.q = this.q[:len(this.q)-1]
		this.vis[t] = false
	}
	cur := f(x, y)
	if this.vis[cur] {
		return -1
	}
	this.q = append([]int{cur}, this.q...)
	this.vis[cur] = true
	return this.score
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * obj := Constructor(width, height, food);
 * param_1 := obj.Move(direction);
 */
```

#### TypeScript

```ts
class SnakeGame {
    private m: number;
    private n: number;
    private food: number[][];
    private score: number;
    private idx: number;
    private q: number[];
    private vis: Set<number>;

    constructor(width: number, height: number, food: number[][]) {
        this.m = height;
        this.n = width;
        this.food = food;
        this.score = 0;
        this.idx = 0;
        this.q = [0];
        this.vis = new Set([0]);
    }

    move(direction: string): number {
        const p = this.q[0];
        const i = Math.floor(p / this.n);
        const j = p % this.n;
        let x = i;
        let y = j;
        if (direction === 'U') {
            --x;
        } else if (direction === 'D') {
            ++x;
        } else if (direction === 'L') {
            --y;
        } else {
            ++y;
        }
        if (x < 0 || x >= this.m || y < 0 || y >= this.n) {
            return -1;
        }
        if (
            this.idx < this.food.length &&
            x === this.food[this.idx][0] &&
            y === this.food[this.idx][1]
        ) {
            ++this.score;
            ++this.idx;
        } else {
            const t = this.q.pop()!;
            this.vis.delete(t);
        }
        const cur = x * this.n + y;
        if (this.vis.has(cur)) {
            return -1;
        }
        this.q.unshift(cur);
        this.vis.add(cur);
        return this.score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * var obj = new SnakeGame(width, height, food)
 * var param_1 = obj.move(direction)
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
