# [03.06. Animal Shelter](https://leetcode-cn.com/problems/animal-shelter-lcci)

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


### Python3

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
