# [面试题 03.06. 动物收容所](https://leetcode-cn.com/problems/animal-shelter-lcci)

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
<!-- 这里可写通用的实现逻辑 -->
双队列存储。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class AnimalShelf:

    def __init__(self):
        self.cats = []
        self.dogs = []

    def enqueue(self, animal: List[int]) -> None:
        if animal[1] == 0:
            self.cats.insert(0, animal[0])
        else:
            self.dogs.insert(0, animal[0])

    def dequeueAny(self) -> List[int]:
        if len(self.dogs) == 0: return self.dequeueCat()
        if len(self.cats) == 0: return self.dequeueDog()
        return self.dequeueDog() if self.dogs[-1] < self.cats[-1] else self.dequeueCat()

    def dequeueDog(self) -> List[int]:
        return [-1, -1] if len(self.dogs) == 0 else [self.dogs.pop(), 1]

    def dequeueCat(self) -> List[int]:
        return [-1, -1] if len(self.cats) == 0 else [self.cats.pop(), 0]


# Your AnimalShelf object will be instantiated and called as such:
# obj = AnimalShelf()
# obj.enqueue(animal)
# param_2 = obj.dequeueAny()
# param_3 = obj.dequeueDog()
# param_4 = obj.dequeueCat()
```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class AnimalShelf {
    Queue<Integer> cats;
    Queue<Integer> dogs;
    public AnimalShelf() {
        cats = new LinkedList<>();
        dogs = new LinkedList<>();
    }
    
    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            cats.offer(animal[0]);
        } else {
            dogs.offer(animal[0]);
        }
    }
    
    public int[] dequeueAny() {
        return dogs.isEmpty() ? dequeueCat() : (cats.isEmpty() ? dequeueDog() : (dogs.peek() < cats.peek() ? dequeueDog() : dequeueCat()));
    }
    
    public int[] dequeueDog() {
        return dogs.isEmpty() ? new int[]{-1, -1} : new int[]{dogs.poll(), 1};
    }
    
    public int[] dequeueCat() {
        return cats.isEmpty() ? new int[]{-1, -1} : new int[]{cats.poll(), 0};
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

### ...
```

```
