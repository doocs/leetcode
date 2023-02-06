# [1603. Design Parking System](https://leetcode.com/problems/design-parking-system)

[中文文档](/solution/1600-1699/1603.Design%20Parking%20System/README.md)

## Description

<p>Design a parking system for a parking lot. The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.</p>

<p>Implement the <code>ParkingSystem</code> class:</p>

<ul>
	<li><code>ParkingSystem(int big, int medium, int small)</code> Initializes object of the <code>ParkingSystem</code> class. The number of slots for each parking space are given as part of the constructor.</li>
	<li><code>bool addCar(int carType)</code> Checks whether there is a parking space of <code>carType</code> for the car that wants to get into the parking lot. <code>carType</code> can be of three kinds: big, medium, or small, which are represented by <code>1</code>, <code>2</code>, and <code>3</code> respectively. <strong>A car can only park in a parking space of its </strong><code>carType</code>. If there is no space available, return <code>false</code>, else park the car in that size space and return <code>true</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;ParkingSystem&quot;, &quot;addCar&quot;, &quot;addCar&quot;, &quot;addCar&quot;, &quot;addCar&quot;]
[[1, 1, 0], [1], [2], [3], [1]]
<strong>Output</strong>
[null, true, true, false, false]

<strong>Explanation</strong>
ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
parkingSystem.addCar(1); // return true because there is 1 available slot for a big car
parkingSystem.addCar(2); // return true because there is 1 available slot for a medium car
parkingSystem.addCar(3); // return false because there is no available slot for a small car
parkingSystem.addCar(1); // return false because there is no available slot for a big car. It is already occupied.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= big, medium, small &lt;= 1000</code></li>
	<li><code>carType</code> is <code>1</code>, <code>2</code>, or <code>3</code></li>
	<li>At most <code>1000</code> calls will be made to <code>addCar</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class ParkingSystem:

    def __init__(self, big: int, medium: int, small: int):
        self.cnt = [0, big, medium, small]

    def addCar(self, carType: int) -> bool:
        if self.cnt[carType] == 0:
            return False
        self.cnt[carType] -= 1
        return True


# Your ParkingSystem object will be instantiated and called as such:
# obj = ParkingSystem(big, medium, small)
# param_1 = obj.addCar(carType)
```

### **Java**

```java
class ParkingSystem {
    private int[] cnt;

    public ParkingSystem(int big, int medium, int small) {
        cnt = new int[] {0, big, medium, small};
    }

    public boolean addCar(int carType) {
        if (cnt[carType] == 0) {
            return false;
        }
        --cnt[carType];
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
```

### **C++**

```cpp
class ParkingSystem {
public:
    vector<int> cnt;

    ParkingSystem(int big, int medium, int small) {
        cnt = {0, big, medium, small};
    }

    bool addCar(int carType) {
        if (cnt[carType] == 0) return false;
        --cnt[carType];
        return true;
    }
};

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem* obj = new ParkingSystem(big, medium, small);
 * bool param_1 = obj->addCar(carType);
 */
```

### **Go**

```go
type ParkingSystem struct {
	cnt []int
}

func Constructor(big int, medium int, small int) ParkingSystem {
	return ParkingSystem{[]int{0, big, medium, small}}
}

func (this *ParkingSystem) AddCar(carType int) bool {
	if this.cnt[carType] == 0 {
		return false
	}
	this.cnt[carType]--
	return true
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * obj := Constructor(big, medium, small);
 * param_1 := obj.AddCar(carType);
 */
```

### **TypeScript**

```ts
class ParkingSystem {
    private count: [number, number, number];

    constructor(big: number, medium: number, small: number) {
        this.count = [big, medium, small];
    }

    addCar(carType: number): boolean {
        if (this.count[carType - 1] === 0) {
            return false;
        }
        this.count[carType - 1]--;
        return true;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * var obj = new ParkingSystem(big, medium, small)
 * var param_1 = obj.addCar(carType)
 */
```

### **Rust**

```rust
struct ParkingSystem {
    count: [i32; 3],
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl ParkingSystem {

    fn new(big: i32, medium: i32, small: i32) -> Self {
        Self {
            count: [big, medium, small]
        }
    }

    fn add_car(&mut self, car_type: i32) -> bool {
        let i = (car_type - 1) as usize;
        if self.count[i] == 0 {
            return false;
        }
        self.count[i] -= 1;
        true
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * let obj = ParkingSystem::new(big, medium, small);
 * let ret_1: bool = obj.add_car(carType);
 */
```

### **C**

```c
typedef struct {
    int *count;
} ParkingSystem;


ParkingSystem *parkingSystemCreate(int big, int medium, int small) {
    ParkingSystem *res = malloc(sizeof(ParkingSystem));
    res->count = malloc(sizeof(int) * 3);
    res->count[0] = big;
    res->count[1] = medium;
    res->count[2] = small;
    return res;
}

bool parkingSystemAddCar(ParkingSystem *obj, int carType) {
    int i = carType - 1;
    if (!obj->count[i]) {
        return 0;
    }
    obj->count[i]--;
    return 1;
}

void parkingSystemFree(ParkingSystem *obj) {
    free(obj);
}

/**
 * Your ParkingSystem struct will be instantiated and called as such:
 * ParkingSystem* obj = parkingSystemCreate(big, medium, small);
 * bool param_1 = parkingSystemAddCar(obj, carType);

 * parkingSystemFree(obj);
*/
```

### **...**

```

```

<!-- tabs:end -->
