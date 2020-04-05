package de.inetsoftware.jwebassembly.api.java.util.concurrent.atomic;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement for java.util.concurrent.atomic.AtomicInteger without using of Unsafe.
 * TODO this class is not thread safe anymore. This must be rewritten if we supports threads.
 *
 * @author Volker Berlin
 */
@Replace( "java/util/concurrent/atomic/AtomicInteger" )
public class ReplacementForAtomicInteger extends Number implements java.io.Serializable {

    private volatile int value;

    public ReplacementForAtomicInteger( int initialValue ) {
        value = initialValue;
    }

    public ReplacementForAtomicInteger() {
    }

    public final int get() {
        return value;
    }

    public final void set( int newValue ) {
        value = newValue;
    }

    public final void lazySet( int newValue ) {
        value = newValue;
    }

    public final int getAndSet( int newValue ) {
        int oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean compareAndSet( int expect, int update ) {
        if( value == expect ) {
            value = update;
            return true;
        }
        return false;
    }

    public final boolean weakCompareAndSet( int expect, int update ) {
        if( value == expect ) {
            value = update;
            return true;
        }
        return false;
    }

    public final int getAndIncrement() {
        return value++;
    }

    public final int getAndDecrement() {
        return value--;
    }

    public final int getAndAdd( int delta ) {
        int oldValue = value;
        value = oldValue + delta;
        return oldValue;
    }

    public final int incrementAndGet() {
        return ++value;
    }

    public final int decrementAndGet() {
        return --value;
    }

    public final int addAndGet( int delta ) {
        int oldValue = value + delta;
        value = oldValue;
        return oldValue;
    }

    public final int getAndUpdate( IntUnaryOperator updateFunction ) {
        int prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsInt( prev );
        } while( !compareAndSet( prev, next ) );
        return prev;
    }

    public final int updateAndGet( IntUnaryOperator updateFunction ) {
        int prev, next;
        do {
            prev = get();
            next = updateFunction.applyAsInt( prev );
        } while( !compareAndSet( prev, next ) );
        return next;
    }

    public final int getAndAccumulate( int x, IntBinaryOperator accumulatorFunction ) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt( prev, x );
        } while( !compareAndSet( prev, next ) );
        return prev;
    }

    public final int accumulateAndGet( int x, IntBinaryOperator accumulatorFunction ) {
        int prev, next;
        do {
            prev = get();
            next = accumulatorFunction.applyAsInt( prev, x );
        } while( !compareAndSet( prev, next ) );
        return next;
    }

    public String toString() {
        return Integer.toString( value );
    }

    public int intValue() {
        return value;
    }

    public long longValue() {
        return (long)value;
    }

    public float floatValue() {
        return (float)value;
    }

    public double doubleValue() {
        return (double)value;
    }
}
