package jun.util;

import org.pcollections.HashTreePMap;
import org.pcollections.PMap;


// Own abstraction for pcollections persistent hash
// map with more usable api.

class ImmutableHashMap {
    public final internmap = HashTreePMap.empty();

    static def makeFrom(final Map map) {
        def _map = HashTreePMap.from(map);
        return new ImmutableHashMap(_map);
    }

    public ImmutableHashMap() {}

    public ImmutableHashMap(final Map map) {
        this.internmap = HashTreePMap.from(map);
    }

    public ImmutableHashMap(final ImmutableHashMap map) {
        this.internmap = map.internmap;
    }

    public ImmutableHashMap(final PMap map) {
        this.internmap = map;
    }

    // public Object dissoc(final Object key) {
    //     def _map = this.internmap.minus(key)
    //     return new ImmutableHashMap(_map);
    // }

    public Object dissoc(final Object... keys) {
        def current = keys.first();
        def _map = keys.inject(this.internmap) { acc, val -> acc.minus(val); }
        return new ImmutableHashMap(_map);
    }

    public Object assoc(final Object key, final Object value) {
        def _map = this.internmap.plus(key, value);
        return new ImmutableHashMap(_map);
    }

    public Object assoc(final Map opts) {
        def _map = this.internmap.plusAll(opts);
        return new ImmutableHashMap(_map);
    }

    public Boolean hasKey(final Object key) {
        return this.internmap.containsKey(key);
    }

    public Object get(key) {
        this.internmap.get(key);
    }

    public List keys() {
        return new ArrayList(this.internmap.keySet());
    }
}


