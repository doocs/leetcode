# [面试题59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
请定义一个队列并实现函数 `max_value` 得到队列里的最大值，要求函数`max_value`、`push_back` 和 `pop_front` 的**均摊**时间复杂度都是O(1)。

若队列为空，`pop_front` 和 `max_value` 需要返回 -1

**示例 1:**

```
输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

**示例 2:**

```
输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```

**限制：**
- `1 <= push_back,pop_front,max_value的总操作数 <= 10000`
- `1 <= value <= 10^5`

## 解法
<!-- 这里可写通用的实现逻辑 -->


### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->
```python

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->
```java

```

### JavaScript
```js
var MaxQueue = function() {
    this.queue = []
    this.maxValue = -Infinity
    this.maxIdx = -1
};

/**
 * @return {number}
 */
MaxQueue.prototype.max_value = function() {
    if(!this.queue.length) return -1
    return this.maxValue
};

/** 
 * @param {number} value
 * @return {void}
 */
MaxQueue.prototype.push_back = function(value) {
    this.queue.push(value)
    if(value >= this.maxValue) {
        this.maxIdx = this.queue.length-1
        this.maxValue = value
    }
};

/**
 * @return {number}
 */
MaxQueue.prototype.pop_front = function() {
    if(!this.queue.length) return -1
    let a = this.queue.shift()
    this.maxIdx--
    if(this.maxIdx < 0) {
        let tmp = -Infinity
        let id = -1
        for(let i=0;i<this.queue.length;i++) {
            if(this.queue[i] > tmp) {
                tmp = this.queue[i]
                id = i
            }
        }
        this.maxIdx = id
        this.maxValue = tmp
    }
    return a
};
```


### ...
```

```