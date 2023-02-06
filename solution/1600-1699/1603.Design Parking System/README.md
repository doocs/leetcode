# [1603. 设计停车系统](https://leetcode.cn/problems/design-parking-system)

[English Version](/solution/1600-1699/1603.Design%20Parking%20System/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。</p>

<p>请你实现 <code>ParkingSystem</code> 类：</p>

<ul>
	<li><code>ParkingSystem(int big, int medium, int small)</code> 初始化 <code>ParkingSystem</code> 类，三个参数分别对应每种停车位的数目。</li>
	<li><code>bool addCar(int carType)</code> 检查是否有 <code>carType</code> 对应的停车位。 <code>carType</code> 有三种类型：大，中，小，分别用数字 <code>1</code>， <code>2</code> 和 <code>3</code> 表示。<strong>一辆车只能停在</strong> <strong> </strong><code>carType</code> 对应尺寸的停车位中。如果没有空车位，请返回 <code>false</code> ，否则将该车停入车位并返回 <code>true</code> 。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
[[1, 1, 0], [1], [2], [3], [1]]
<strong>输出：</strong>
[null, true, true, false, false]

<strong>解释：</strong>
ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= big, medium, small <= 1000</code></li>
	<li><code>carType</code> 取值为 <code>1</code>， <code>2</code> 或 <code>3</code></li>
	<li>最多会调用 <code>addCar</code> 函数 <code>1000</code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

为每种车维护一个计数器，初始值为车位的数目。此后，每来一辆车，就将对应类型的计数器减 `1`。当计数器为 `0` 时，说明车位已满。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
