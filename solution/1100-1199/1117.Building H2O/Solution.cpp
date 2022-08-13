class H2O {
private:
    int n_h;
    mutex m_h, m_o;

public:
    H2O() {
        m_o.lock();
        n_h = 2;
    }

    void hydrogen(function<void()> releaseHydrogen) {
        m_h.lock();
        releaseHydrogen();
        n_h--;
        if (n_h > 0)
            m_h.unlock();
        else
            m_o.unlock();
    }

    void oxygen(function<void()> releaseOxygen) {
        m_o.lock();
        releaseOxygen();
        n_h = 2;
        m_h.unlock();
    }
};