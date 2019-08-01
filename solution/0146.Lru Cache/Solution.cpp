class LRUCache {
	private:
		int _cap;
		// 双链表：装着 (key, value) 元组
		list<pair<int, int>> cache;
		// 哈希表：key 映射到 (key, value) 在 cache 中的位置
		unordered_map<int, list<pair<int, int>>::iterator> umap;
	public:
	   LRUCache(int capacity) {
			_cap = capacity;
			
		}
		
		int get(int key) {
			auto iter = umap.find(key);
			if(iter == umap.end())return -1;
			
			pair<int,int> kv = *umap[key];
			cache.erase(umap[key]);//改
			cache.push_front(kv);
			umap[key] = cache.begin();
			
			return kv.second;
		}
		
		void put(int key, int value) {
			auto iter = umap.find(key);
			if(iter != umap.end()){ //存在于缓存
				
				cache.erase(umap[key]);
				cache.push_front(make_pair(key,value));
				umap[key] = cache.begin(); 
				
				return;
			}
			
			
			//不在缓存中
			if(cache.size() == _cap) //满了
			{
				
				auto iter = cache.back();
				umap.erase(iter.first);
				cache.pop_back();
				
				cache.push_front(make_pair(key,value));
				umap[key] = cache.begin();
				
			}
			else                    //没满
			{
				cache.push_front(make_pair(key,value));
				umap[key] = cache.begin();
				
			}
		}
	};