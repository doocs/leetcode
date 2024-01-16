# [353. Design Snake Game](https://leetcode.com/problems/design-snake-game)

[中文文档](/solution/0300-0399/0353.Design%20Snake%20Game/README.md)

## Description

<p>Design a <a href="https://en.wikipedia.org/wiki/Snake_(video_game)" target="_blank">Snake game</a> that is played on a device with screen size <code>height x width</code>. <a href="http://patorjk.com/games/snake/" target="_blank">Play the game online</a> if you are not familiar with the game.</p>

<p>The snake is initially positioned at the top left corner <code>(0, 0)</code> with a length of <code>1</code> unit.</p>

<p>You are given an array <code>food</code> where <code>food[i] = (r<sub>i</sub>, c<sub>i</sub>)</code> is the row and column position of a piece of food that the snake can eat. When a snake eats a piece of food, its length and the game&#39;s score both increase by <code>1</code>.</p>

<p>Each piece of food appears one by one on the screen, meaning the second piece of food will not appear until the snake eats the first piece of food.</p>

<p>When a piece of food appears on the screen, it is <strong>guaranteed</strong> that it will not appear on a block occupied by the snake.</p>

<p>The game is over if the snake goes out of bounds (hits a wall) or if its head occupies a space that its body occupies <strong>after</strong> moving (i.e. a snake of length 4 cannot run into itself).</p>

<p>Implement the <code>SnakeGame</code> class:</p>

<ul>
	<li><code>SnakeGame(int width, int height, int[][] food)</code> Initializes the object with a screen of size <code>height x width</code> and the positions of the <code>food</code>.</li>
	<li><code>int move(String direction)</code> Returns the score of the game after applying one <code>direction</code> move by the snake. If the game is over, return <code>-1</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0353.Design%20Snake%20Game/images/snake.jpg" style="width: 800px; height: 302px;" />
<pre>
<strong>Input</strong>
[&quot;SnakeGame&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;, &quot;move&quot;]
[[3, 2, [[1, 2], [0, 1]]], [&quot;R&quot;], [&quot;D&quot;], [&quot;R&quot;], [&quot;U&quot;], [&quot;L&quot;], [&quot;U&quot;]]
<strong>Output</strong>
[null, 0, 0, 1, 1, 2, -1]

<strong>Explanation</strong>
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move(&quot;R&quot;); // return 0
snakeGame.move(&quot;D&quot;); // return 0
snakeGame.move(&quot;R&quot;); // return 1, snake eats the first piece of food. The second piece of food appears at (0, 1).
snakeGame.move(&quot;U&quot;); // return 1
snakeGame.move(&quot;L&quot;); // return 2, snake eats the second food. No more food appears.
snakeGame.move(&quot;U&quot;); // return -1, game over because snake collides with border

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= width, height &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= food.length &lt;= 50</code></li>
	<li><code>food[i].length == 2</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; height</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; width</code></li>
	<li><code>direction.length == 1</code></li>
	<li><code>direction</code> is <code>&#39;U&#39;</code>, <code>&#39;D&#39;</code>, <code>&#39;L&#39;</code>, or <code>&#39;R&#39;</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>move</code>.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

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

<!-- end -->
