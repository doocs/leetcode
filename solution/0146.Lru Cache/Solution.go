type list struct{   //双向链表,用于保存key:value
    prev,next *list
    key,val int
}

type LRUCache struct {
    _len,_cap int
    front,back *list    //头尾指针
    umap map[int]*list  //hash表
}
  

func Constructor(capacity int) LRUCache {
    return LRUCache{
        _len : 0,
        _cap : capacity,
        front: nil,
        back : nil,
        umap : make(map[int]*list,capacity),
    }
}


func (this *LRUCache) Get(key int) int {
    if node,ok := this.umap[key];ok{  //存在
        
        this.push_front(node)
        return node.val
    }
    
    return -1
}


func (this *LRUCache) Put(key int, value int)  {
    if node,ok := this.umap[key];ok{ 
        node.val = value
        this.push_front(node)
        return 
    }
    
    //找不到
    if this._len == this._cap{
        delete(this.umap,this.back.key)
        this.pop_back()
    }else{
        this._len++
    }
    
    node := &list{
        prev:nil,
        next:nil,
        key:key,
        val:value,
    }
    
    this.umap[key] = node
    this.insert_front(node)  
}


func (this *LRUCache) push_front(node *list){
    switch node{    //先删除，再插入
    case this.front:
        return
    case this.back:
        this.pop_back()
    default:
        node.prev.next = node.next
        node.next.prev = node.prev
    }
    
    this.insert_front(node)
}

func (this *LRUCache) pop_back(){
    if this.back.prev != nil{ //链表长度大于1
        this.back.prev.next = nil
    }else{                      //链表长度小于等于1
        this.front = nil
    }
    
    this.back = this.back.prev
}


func (this *LRUCache)insert_front(node *list){
    if this.back == nil{
        //空表
        this.back = node
    }else{ //头插法
        node.next = this.front
        this.front.prev = node
    }
    
    this.front = node
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */