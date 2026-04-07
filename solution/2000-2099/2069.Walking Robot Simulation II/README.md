---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/README.md
rating: 1918
source: 第 65 场双周赛 Q2
tags:
    - 设计
    - 模拟
---

<!-- problem:start -->

# [2069. 模拟行走机器人 II](https://leetcode.cn/problems/walking-robot-simulation-ii)

[English Version](/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个在 XY 平面上的&nbsp;<code>width x height</code>&nbsp;的网格图，<strong>左下角</strong>&nbsp;的格子为&nbsp;<code>(0, 0)</code>&nbsp;，<strong>右上角</strong>&nbsp;的格子为&nbsp;<code>(width - 1, height - 1)</code>&nbsp;。网格图中相邻格子为四个基本方向之一（<code>"North"</code>，<code>"East"</code>，<code>"South"</code>&nbsp;和&nbsp;<code>"West"</code>）。一个机器人 <strong>初始</strong>&nbsp;在格子&nbsp;<code>(0, 0)</code>&nbsp;，方向为&nbsp;<code>"East"</code>&nbsp;。</p>

<p>机器人可以根据指令移动指定的 <strong>步数</strong>&nbsp;。每一步，它可以执行以下操作。</p>

<ol>
	<li>沿着当前方向尝试 <strong>往前一步</strong>&nbsp;。</li>
	<li>如果机器人下一步将到达的格子 <strong>超出了边界</strong>&nbsp;，机器人会 <strong>逆时针</strong>&nbsp;转 90 度，然后再尝试往前一步。</li>
</ol>

<p>如果机器人完成了指令要求的移动步数，它将停止移动并等待下一个指令。</p>

<p>请你实现&nbsp;<code>Robot</code>&nbsp;类：</p>

<ul>
	<li><code>Robot(int width, int height)</code>&nbsp;初始化一个&nbsp;<code>width x height</code>&nbsp;的网格图，机器人初始在&nbsp;<code>(0, 0)</code>&nbsp;，方向朝&nbsp;<code>"East"</code>&nbsp;。</li>
	<li><code>void step(int num)</code>&nbsp;给机器人下达前进&nbsp;<code>num</code>&nbsp;步的指令。</li>
	<li><code>int[] getPos()</code>&nbsp;返回机器人当前所处的格子位置，用一个长度为 2 的数组&nbsp;<code>[x, y]</code>&nbsp;表示。</li>
	<li><code>String getDir()</code>&nbsp;返回当前机器人的朝向，为&nbsp;<code>"North"</code>&nbsp;，<code>"East"</code>&nbsp;，<code>"South"</code>&nbsp;或者&nbsp;<code>"West"</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="example-1" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2069.Walking%20Robot%20Simulation%20II/images/example-1.png" style="width: 498px; height: 268px;" /></p>

<pre>
<strong>输入：</strong>
["Robot", "step", "step", "getPos", "getDir", "step", "step", "step", "getPos", "getDir"]
[[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
<strong>输出：</strong>
[null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]

<strong>解释：</strong>
Robot robot = new Robot(6, 3); // 初始化网格图，机器人在 (0, 0) ，朝东。
robot.step(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
robot.step(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
robot.getPos(); // 返回 [4, 0]
robot.getDir(); // 返回 "East"
robot.step(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
                // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
                // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
robot.step(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 <strong>北</strong> （不是朝西）。
robot.step(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
                // 然后，移动 4 步到 (1, 2) ，并朝西。
robot.getPos(); // 返回 [1, 2]
robot.getDir(); // 返回 "West"

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= width, height &lt;= 100</code></li>
	<li><code>1 &lt;= num &lt;= 10<sup>5</sup></code></li>
	<li><code>step</code> ，<code>getPos</code>&nbsp;和&nbsp;<code>getDir</code>&nbsp;<strong>总共&nbsp;</strong>调用次数不超过&nbsp;<code>10<sup>4</sup></code>&nbsp;次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分类讨论

我们记 $mx = width - 1$ 和 $my = height - 1$，则机器人的运动轨迹是一个以 $(0, 0)$ 为左下角，而 $(mx, my)$ 为右上角的矩形的边界。我们可以将机器人的运动轨迹分为四段：

1. 从 $(0, 0)$ 沿着 $x$ 轴正方向运动到 $(mx, 0)$，此时机器人的朝向为 "East"。
2. 从 $(mx, 0)$ 沿着 $y$ 轴正方向运动到 $(mx, my)$，此时机器人的朝向为 "North"。
3. 从 $(mx, my)$ 沿着 $x$ 轴负方向运动到 $(0, my)$，此时机器人的朝向为 "West"。
4. 从 $(0, my)$ 沿着 $y$ 轴负方向运动到 $(0, 0)$，此时机器人的朝向为 "South"。

因此，我们可以将机器人的运动轨迹看作是一个长度为 $p = 2 \cdot mx + 2 \cdot my$ 的循环。对于每次调用 `step(num)`，我们可以将机器人的当前位置加上 `num`，然后对 $p$ 取模，得到机器人的新位置。根据机器人的新位置，我们可以判断出机器人的朝向和坐标。

注意，如果机器人没有移动过，那么它的朝向应该是 "East"。如果机器人移动过，且位置为 $(0, 0)$，则机器人的朝向应该是 "South"。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Robot:

    def __init__(self, width: int, height: int):
        self.mx = width - 1
        self.my = height - 1
        self.p = 2 * self.mx + 2 * self.my
        self.cur = 0
        self.moved = False

    def step(self, num: int) -> None:
        self.moved = True
        self.cur = (self.cur + num) % self.p

    def getPos(self) -> List[int]:
        d = self.cur
        mx, my = self.mx, self.my
        if 0 <= d <= mx:
            return [d, 0]
        if mx < d <= mx + my:
            return [mx, d - mx]
        if mx + my < d <= 2 * mx + my:
            return [mx - (d - (mx + my)), my]
        return [0, my - (d - (2 * mx + my))]

    def getDir(self) -> str:
        d = self.cur
        mx, my = self.mx, self.my
        if not self.moved:
            return "East"
        if 1 <= d <= mx:
            return "East"
        elif mx < d <= mx + my:
            return "North"
        elif mx + my < d <= 2 * mx + my:
            return "West"
        return "South"


# Your Robot object will be instantiated and called as such:
# obj = Robot(width, height)
# obj.step(num)
# param_2 = obj.getPos()
# param_3 = obj.getDir()
```

#### Java

```java
class Robot {

    private int mx, my, p, cur;
    private boolean moved;

    public Robot(int width, int height) {
        this.mx = width - 1;
        this.my = height - 1;
        this.p = 2 * this.mx + 2 * this.my;
        this.cur = 0;
        this.moved = false;
    }

    public void step(int num) {
        this.moved = true;
        this.cur = (this.cur + num) % this.p;
    }

    public int[] getPos() {
        int d = this.cur;
        int mx = this.mx, my = this.my;

        if (0 <= d && d <= mx) {
            return new int[] {d, 0};
        }
        if (mx < d && d <= mx + my) {
            return new int[] {mx, d - mx};
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return new int[] {mx - (d - (mx + my)), my};
        }
        return new int[] {0, my - (d - (2 * mx + my))};
    }

    public String getDir() {
        int d = this.cur;
        int mx = this.mx, my = this.my;

        if (!this.moved) {
            return "East";
        }
        if (1 <= d && d <= mx) {
            return "East";
        } else if (mx < d && d <= mx + my) {
            return "North";
        } else if (mx + my < d && d <= 2 * mx + my) {
            return "West";
        }
        return "South";
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
```

#### C++

```cpp
class Robot {
public:
    int mx, my, p, cur;
    bool moved;

    Robot(int width, int height) {
        mx = width - 1;
        my = height - 1;
        p = 2 * mx + 2 * my;
        cur = 0;
        moved = false;
    }

    void step(int num) {
        moved = true;
        cur = (cur + num) % p;
    }

    vector<int> getPos() {
        int d = cur;
        int mx = this->mx, my = this->my;

        if (0 <= d && d <= mx) {
            return {d, 0};
        }
        if (mx < d && d <= mx + my) {
            return {mx, d - mx};
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return {mx - (d - (mx + my)), my};
        }
        return {0, my - (d - (2 * mx + my))};
    }

    string getDir() {
        int d = cur;
        int mx = this->mx, my = this->my;

        if (!moved) {
            return "East";
        }
        if (1 <= d && d <= mx) {
            return "East";
        } else if (mx < d && d <= mx + my) {
            return "North";
        } else if (mx + my < d && d <= 2 * mx + my) {
            return "West";
        }
        return "South";
    }
};

/**
 * Your Robot object will be instantiated and called as such:
 * Robot* obj = new Robot(width, height);
 * obj->step(num);
 * vector<int> param_2 = obj->getPos();
 * string param_3 = obj->getDir();
 */
```

#### Go

```go
type Robot struct {
	mx, my, p, cur int
	moved          bool
}

func Constructor(width int, height int) Robot {
	mx := width - 1
	my := height - 1
	return Robot{
		mx:    mx,
		my:    my,
		p:     2*mx + 2*my,
		cur:   0,
		moved: false,
	}
}

func (this *Robot) Step(num int) {
	this.moved = true
	this.cur = (this.cur + num) % this.p
}

func (this *Robot) GetPos() []int {
	d := this.cur
	mx, my := this.mx, this.my

	if 0 <= d && d <= mx {
		return []int{d, 0}
	}
	if mx < d && d <= mx+my {
		return []int{mx, d - mx}
	}
	if mx+my < d && d <= 2*mx+my {
		return []int{mx - (d - (mx + my)), my}
	}
	return []int{0, my - (d - (2*mx + my))}
}

func (this *Robot) GetDir() string {
	d := this.cur
	mx, my := this.mx, this.my

	if !this.moved {
		return "East"
	}
	if 1 <= d && d <= mx {
		return "East"
	} else if mx < d && d <= mx+my {
		return "North"
	} else if mx+my < d && d <= 2*mx+my {
		return "West"
	}
	return "South"
}

/**
 * Your Robot object will be instantiated and called as such:
 * obj := Constructor(width, height);
 * obj.Step(num);
 * param_2 := obj.GetPos();
 * param_3 := obj.GetDir();
 */
```

#### TypeScript

```ts
class Robot {
    private mx: number;
    private my: number;
    private p: number;
    private cur: number;
    private moved: boolean;

    constructor(width: number, height: number) {
        this.mx = width - 1;
        this.my = height - 1;
        this.p = 2 * this.mx + 2 * this.my;
        this.cur = 0;
        this.moved = false;
    }

    step(num: number): void {
        this.moved = true;
        this.cur = (this.cur + num) % this.p;
    }

    getPos(): number[] {
        const d = this.cur;
        const mx = this.mx,
            my = this.my;

        if (0 <= d && d <= mx) {
            return [d, 0];
        }
        if (mx < d && d <= mx + my) {
            return [mx, d - mx];
        }
        if (mx + my < d && d <= 2 * mx + my) {
            return [mx - (d - (mx + my)), my];
        }
        return [0, my - (d - (2 * mx + my))];
    }

    getDir(): string {
        const d = this.cur;
        const mx = this.mx,
            my = this.my;

        if (!this.moved) {
            return 'East';
        }
        if (1 <= d && d <= mx) {
            return 'East';
        } else if (mx < d && d <= mx + my) {
            return 'North';
        } else if (mx + my < d && d <= 2 * mx + my) {
            return 'West';
        }
        return 'South';
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * var obj = new Robot(width, height)
 * obj.step(num)
 * var param_2 = obj.getPos()
 * var param_3 = obj.getDir()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
