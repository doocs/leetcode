---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/03.06.Animal%20Shelter/README_EN.md
---

# [03.06. Animal Shelter](https://leetcode.cn/problems/animal-shelter-lcci)

[中文文档](/lcci/03.06.Animal%20Shelter/README.md)

## Description

<p>An animal shelter, which holds only dogs and cats, operates on a strictly&quot;first in, first out&quot; basis. People must adopt either the&quot;oldest&quot; (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as <code>enqueue</code>, <code>dequeueAny</code>, <code>dequeueDog</code>, and <code>dequeueCat</code>. You may use the built-in Linked list data structure.</p>

<p><code>enqueue</code> method has a <code>animal</code> parameter, <code>animal[0]</code> represents the number of the animal, <code>animal[1]</code> represents the type of the animal, 0 for cat and 1 for dog.</p>

<p><code>dequeue*</code> method returns <code>[animal number, animal type]</code>, if there&#39;s no animal that can be adopted, return <code>[-1, -1]</code>.</p>

<p><strong>Example1:</strong></p>

<pre>

<strong> Input</strong>: 

[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueCat&quot;, &quot;dequeueDog&quot;, &quot;dequeueAny&quot;]

[[], [[0, 0]], [[1, 0]], [], [], []]

<strong> Output</strong>: 

[null,null,null,[0,0],[-1,-1],[1,0]]

</pre>

<p><strong>Example2:</strong></p>

<pre>

<strong> Input</strong>: 

[&quot;AnimalShelf&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;enqueue&quot;, &quot;dequeueDog&quot;, &quot;dequeueCat&quot;, &quot;dequeueAny&quot;]

[[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]

<strong> Output</strong>: 

[null,null,null,null,[2,1],[0,0],[1,0]]

</pre>

<p><strong>Note:</strong></p>

<ol>
	<li>The number of animals in the shelter will not exceed 20000.</li>
</ol>

## Solutions

### Solution 1: Array of Queues

We define an array $q$ of length $2$ to store the queues of cats and dogs.

In the `enqueue` operation, assuming the animal number is $i$ and the animal type is $j$, we enqueue $i$ into $q[j]$.

In the `dequeueAny` operation, we check whether $q[0]$ is empty, or $q[1]$ is not empty and $q[1][0] < q[0][0]$. If so, we call `dequeueDog`, otherwise we call `dequeueCat`.

In the `dequeueDog` operation, if $q[1]$ is empty, we return $[-1, -1]$, otherwise we return $[q[1].pop(), 1]$.

In the `dequeueCat` operation, if $q[0]$ is empty, we return $[-1, -1]$, otherwise we return $[q[0].pop(), 0]$.

The time complexity of the above operations is $O(1)$, and the space complexity is $O(n)$, where $n$ is the number of animals in the animal shelter.

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
