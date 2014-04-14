package statistics.matcher;

public class QueryBuilder {

    private Matcher q;
    private static Matcher NONE = new None();

    public QueryBuilder(Matcher q) {
        this.q = q;
    }

    public QueryBuilder() {
        q = NONE;
    }

    public Matcher build() {
        return q;
    }

    public QueryBuilder hasFewerThan(int number, String property) {
        return addMatcher(new HasFewerThan(number, property));
    }
    
    public QueryBuilder hasAtLeast(int number, String property) {
        return addMatcher(new HasAtLeast(number, property));
    }

    public QueryBuilder playsIn(String team) {
        return addMatcher(new PlaysIn(team));        
    }

    private QueryBuilder addMatcher(Matcher first) {
        if (q == NONE) {
            q = new All();
        }

        return new QueryBuilder(new And(first, q));
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        return addMatcher(new Or(m1, m2)); 
    }
}
