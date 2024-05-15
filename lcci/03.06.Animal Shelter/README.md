---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.06.Animal%20Shelter/README.md
---

# [面试题 03.06. 动物收容所](https://leetcode.cn/problems/animal-shelter-lcci)

[English Version](/lcci/03.06.Animal%20Shelter/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>动物收容所。有家动物收容所只收容狗与猫，且严格遵守&ldquo;先进先出&rdquo;的原则。在收养该收容所的动物时，收养人只能收养所有动物中&ldquo;最老&rdquo;（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中&ldquo;最老&rdquo;的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如<code>enqueue</code>、<code>dequeueAny</code>、<code>dequeueDog</code>和<code>dequeueCat</code>。允许使用Java内置的LinkedList数据结构。</p>

<p><code>enqueue</code>方法有一个<code>animal</code>参数，<code>animal[0]</code>代表动物编号，<code>animal[1]</code>代表动物种类，其中 0 代表猫，1 代表狗。</p>

<p><code>dequeue*</code>方法返回一个列表<code>[动物编号, 动物种类]</code>，若没有可以收养的动物，则返回<code>[-1,-1]</code>。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：
[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueCat&quot;, &quot;dequeueDog&quot;, &quot;dequeueAny&quot;]
[[], [[0, 0]], [[1, 0]], [], [], []]
<strong> 输出</strong>：
[null,null,null,[0,0],[-1,-1],[1,0]]
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：
[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueDog&quot;, &quot;dequeueCat&quot;, &quot;dequeueAny&quot;]
[[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
<strong> 输出</strong>：
[null,null,null,null,[2,1],[0,0],[1,0]]
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>收纳所的最大容量为20000</li>
</ol>

## 解法

### 方法一：数组嵌套队列

我们定义一个长度为 $2$ 的数组 $q$，用于存放猫和狗的队列。

在 `enqueue` 操作中，假设动物编号为 $i$，动物种类为 $j$，我们将 $i$ 入队到 $q[j]$ 中。

在 `dequeueAny` 操作中，我们判断 $q[0]$ 是否为空，或者 $q[1]$ 不为空且 $q[1][0] < q[0][0]$，如果是，则调用 `dequeueDog`，否则调用 `dequeueCat`。

在 `dequeueDog` 操作中，如果 $q[1]$ 为空，则返回 $[-1, -1]$，否则返回 $[q[1].pop(), 1]$。

在 `dequeueCat` 操作中，如果 $q[0]$ 为空，则返回 $[-1, -1]$，否则返回 $[q[0].pop(), 0]$。

以上操作的时间复杂度均为 $O(1)$，空间复杂度为 $O(n)$，其中 $n$ 为动物收容所中动物的数量。

<!-- tabs:start -->

```python
class AnimalShelf:

    def __init__(self):
        self.q = [deque(), deque()]

    def enqueue(self, animal: List[int]) -> None:
        i, j = animal
        self.q[j].append(i)

    def dequeueAny(self) -> List[int]:
        if not self.q[0] or (self.q[1] and self.q[1][0] < self.q[0][0]):
            return self.dequeueDog()
        return self.dequeueCat()

    def dequeueDog(self) -> List[int]:
        return [-1, -1] if not self.q[1] else [self.q[1].popleft(), 1]

    def dequeueCat(self) -> List[int]:
        return [-1, -1] if not self.q[0] else [self.q[0].popleft(), 0]


# Your AnimalShelf object will be instantiated and called as such:
# obj = AnimalShelf()
# obj.enqueue(animal)
# param_2 = obj.dequeueAny()
# param_3 = obj.dequeueDog()
# param_4 = obj.dequeueCat()
```

```java
class AnimalShelf {
    private Deque<Integer>[] q = new Deque[2];

    public AnimalShelf() {
        Arrays.setAll(q, k -> new ArrayDeque<>());
    }

    public void enqueue(int[] animal) {
        q[animal[1]].offer(animal[0]);
    }

    public int[] dequeueAny() {
        if (q[0].isEmpty() || (!q[1].isEmpty() && q[1].peek() < q[0].peek())) {
            return dequeueDog();
        }
        return dequeueCat();
    }

    public int[] dequeueDog() {
        return q[1].isEmpty() ? new int[] {-1, -1} : new int[] {q[1].poll(), 1};
    }

    public int[] dequeueCat() {
        return q[0].isEmpty() ? new int[] {-1, -1} : new int[] {q[0].poll(), 0};
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf obj = new AnimalShelf();
 * obj.enqueue(animal);
 * int[] param_2 = obj.dequeueAny();
 * int[] param_3 = obj.dequeueDog();
 * int[] param_4 = obj.dequeueCat();
 */
```

```cpp
class AnimalShelf {
public:
    AnimalShelf() {
    }

    void enqueue(vector<int> animal) {
        q[animal[1]].push(animal[0]);
    }

    vector<int> dequeueAny() {
        if (q[0].empty() || (!q[1].empty() && q[1].front() < q[0].front())) {
            return dequeueDog();
        }
        return dequeueCat();
    }

    vector<int> dequeueDog() {
        if (q[1].empty()) {
            return {-1, -1};
        }
        int dog = q[1].front();
        q[1].pop();
        return {dog, 1};
    }

    vector<int> dequeueCat() {
        if (q[0].empty()) {
            return {-1, -1};
        }
        int cat = q[0].front();
        q[0].pop();
        return {cat, 0};
    }

private:
    queue<int> q[2];
};

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf* obj = new AnimalShelf();
 * obj->enqueue(animal);
 * vector<int> param_2 = obj->dequeueAny();
 * vector<int> param_3 = obj->dequeueDog();
 * vector<int> param_4 = obj->dequeueCat();
 */
```

```go
type AnimalShelf struct {
	q [2][]int
}

func Constructor() AnimalShelf {
	return AnimalShelf{}
}

func (this *AnimalShelf) Enqueue(animal []int) {
	this.q[animal[1]] = append(this.q[animal[1]], animal[0])
}

func (this *AnimalShelf) DequeueAny() []int {
	if len(this.q[0]) == 0 || (len(this.q[1]) > 0 && this.q[0][0] > this.q[1][0]) {
		return this.DequeueDog()
	}
	return this.DequeueCat()
}

func (this *AnimalShelf) DequeueDog() []int {
	if len(this.q[1]) == 0 {
		return []int{-1, -1}
	}
	dog := this.q[1][0]
	this.q[1] = this.q[1][1:]
	return []int{dog, 1}
}

func (this *AnimalShelf) DequeueCat() []int {
	if len(this.q[0]) == 0 {
		return []int{-1, -1}
	}
	cat := this.q[0][0]
	this.q[0] = this.q[0][1:]
	return []int{cat, 0}
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Enqueue(animal);
 * param_2 := obj.DequeueAny();
 * param_3 := obj.DequeueDog();
 * param_4 := obj.DequeueCat();
 */
```

```ts
class AnimalShelf {
    private q: number[][] = [[], []];
    constructor() {}

    enqueue(animal: number[]): void {
        const [i, j] = animal;
        this.q[j].push(i);
    }

    dequeueAny(): number[] {
        if (this.q[0].length === 0 || (this.q[1].length > 0 && this.q[0][0] > this.q[1][0])) {
            return this.dequeueDog();
        }
        return this.dequeueCat();
    }

    dequeueDog(): number[] {
        if (this.q[1].length === 0) {
            return [-1, -1];
        }
        return [this.q[1].shift()!, 1];
    }

    dequeueCat(): number[] {
        if (this.q[0].length === 0) {
            return [-1, -1];
        }
        return [this.q[0].shift()!, 0];
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * var obj = new AnimalShelf()
 * obj.enqueue(animal)
 * var param_2 = obj.dequeueAny()
 * var param_3 = obj.dequeueDog()
 * var param_4 = obj.dequeueCat()
 */
```

```rust
use std::collections::VecDeque;

struct AnimalShelf {
    q: [VecDeque<i32>; 2],
}

impl AnimalShelf {
    fn new() -> Self {
        AnimalShelf {
            q: [VecDeque::new(), VecDeque::new()],
        }
    }

    fn enqueue(&mut self, animal: Vec<i32>) {
        self.q[animal[1] as usize].push_back(animal[0]);
    }

    fn dequeue_any(&mut self) -> Vec<i32> {
        if
            self.q[0].is_empty() ||
            (!self.q[1].is_empty() && self.q[1].front().unwrap() < self.q[0].front().unwrap())
        {
            self.dequeue_dog()
        } else {
            self.dequeue_cat()
        }
    }

    fn dequeue_dog(&mut self) -> Vec<i32> {
        if self.q[1].is_empty() {
            vec![-1, -1]
        } else {
            let dog = self.q[1].pop_front().unwrap();
            vec![dog, 1]
        }
    }

    fn dequeue_cat(&mut self) -> Vec<i32> {
        if self.q[0].is_empty() {
            vec![-1, -1]
        } else {
            let cat = self.q[0].pop_front().unwrap();
            vec![cat, 0]
        }
    }
}/**
 * Your AnimalShelf object will be instantiated and called as such:
 * let obj = AnimalShelf::new();
 * obj.enqueue(animal);
 * let ret_2: Vec<i32> = obj.dequeue_any();
 * let ret_3: Vec<i32> = obj.dequeue_dog();
 * let ret_4: Vec<i32> = obj.dequeue_cat();
 */
```

```swift
class AnimalShelf {
    private var q: [[Int]] = Array(repeating: [], count: 2)

    init() {
    }

    func enqueue(_ animal: [Int]) {
        q[animal[1]].append(animal[0])
    }

    func dequeueAny() -> [Int] {
        if q[0].isEmpty || (!q[1].isEmpty && q[1].first! < q[0].first!) {
            return dequeueDog()
        }
        return dequeueCat()
    }

    func dequeueDog() -> [Int] {
        return q[1].isEmpty ? [-1, -1] : [q[1].removeFirst(), 1]
    }

    func dequeueCat() -> [Int] {
        return q[0].isEmpty ? [-1, -1] : [q[0].removeFirst(), 0]
    }
}

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * let obj = new AnimalShelf();
 * obj.enqueue(animal);
 * let param_2 = obj.dequeueAny();
 * let param_3 = obj.dequeueDog();
 * let param_4 = obj.dequeueCat();
 */
```

<!-- tabs:end -->

<!-- end -->
