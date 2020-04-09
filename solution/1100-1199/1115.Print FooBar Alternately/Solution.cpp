class FooBar {
private:
    int n;
    std::mutex _mutex1;
    std::mutex _mutex2;

public:
    FooBar(int n) {
        this->n = n;
        _mutex2.lock();
    }

    void foo(function<void()> printFoo) {
        
        for (int i = 0; i < n; i++) {
            _mutex1.lock();
        	printFoo();
            _mutex2.unlock();
        }
    }

    void bar(function<void()> printBar) {
        
        for (int i = 0; i < n; i++) {
            _mutex2.lock();
        	printBar();
            _mutex1.unlock();
        }
    }
};