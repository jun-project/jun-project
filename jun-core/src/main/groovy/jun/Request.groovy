package jun;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jun.util.ImmutableHashMap;

class Request extends ImmutableHashMap {
    Object propertyMissing(final Object name) {
        if (this.hasKey(name) ) {
            return this.get(name);
        }
        return ""
    }

    public Request(final Map opts) {
        super(opts);
    }

    public Request(final ImmutableHashMap map) {
        super(map);
    }

    public Request assoc(final Map map) {
        return new Request(super.assoc(map));
    }

    public Request assoc(final Object key, final Object value) {
        return new Request(super.assoc(key, value));
    }

    public Request dissoc(final Objects... keys) {
        return new Request(this.dissoc(keys));
    }
}