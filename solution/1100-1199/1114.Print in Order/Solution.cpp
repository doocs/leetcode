class Foo {
public:
    Foo() {
        _mutex1.lock();
        _mutex2.lock();
    }

    void first(function<void()> printFirst) {
        
        printFirst();
        _mutex1.unlock();
    }

    void second(function<void()> printSecond) {
        
        _mutex1.lock();
        printSecond();
        _mutex1.unlock();
        _mutex2.unlock();
    }

    void third(function<void()> printThird) {
        
        _mutex2.lock();
        printThird();
        _mutex2.unlock();
    }
    
    
private:
    std::mutex _mutex1;
    std::mutex _mutex2;
    
};
