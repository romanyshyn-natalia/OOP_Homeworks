package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AsIntStream implements IntStream, Iterable<Integer> {
    private Iterator<Integer> streamIterator;
    private final List<Integer> elements;

    private AsIntStream(int... values) {
        // To Do
        this.elements = new ArrayList<>();
        for (int value: values) {
            elements.add(value);
        }
    }

    public static IntStream of(int... values) {
        //створює початковий потік на основі масиву цілих чисел
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        //середнє значення чисел в потоці.
        //Термінальній метод. IllegalArgumentException - if empty
        if (elements.size() == 0) {
            throw new IllegalArgumentException("The stream is empty");
        }
        double sum = 0;
        int elementsNumber = 0;
        for (int value: this) {
            sum += value;
            elementsNumber++;
        }
        return sum / elementsNumber;
    }

    @Override
    public Integer max() {
        //максимальне / мінімальне значення числа в потоці.
        // Термінальній метод. IllegalArgumentException - if empty
        if (elements.size() == 0) {
            throw new IllegalArgumentException("The stream is empty");
        }
        int maximum = Integer.MIN_VALUE;
        for (int value: this) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    @Override
    public Integer min() {
        if (elements.size() == 0) {
            throw new IllegalArgumentException("The stream is empty");
        }
        int minimum = Integer.MAX_VALUE;
        for (int value: this) {
            if (value < minimum) {
                minimum = value;
            }
        }
        return minimum;
    }

    @Override
    public long count() {
        //кількість значень (елементів) в потоці. Термінальній метод.
        long counter = 0;
        for (int ignored: this) {
            counter++;
        }
        return counter;
    }

    @Override
    public Integer sum() {
        //сума всіх значень в потоці. Термінальній метод.
        // IllegalArgumentException - if empty
        if (elements.size() == 0) {
            throw new IllegalArgumentException("The stream is empty");
        }
        return this.reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        //для кожного значення з потоку перевіряє його на предмет
        // чи задовольняє воно умові в реалізації IntPredicate,
        // якщо так - повертає його в результуючий потік, якщо ні - викидає
        this.streamIterator = new Iterator<Integer>() {
            private final Iterator<Integer> oldIterator = iterator();

            @Override
            public boolean hasNext() {
                return oldIterator.hasNext();
            }

            @Override
            public Integer next() {
                int value = oldIterator.next();
                while (!predicate.test(value)) {
                    value = oldIterator.next();
                }
                return value;
            }
        };
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        //для кожного значення з потоку виконує
        // операцію зазначену в реалізації IntConsumer.
        // Даний метод є термінальним і нічого не повертає
        for (int value: this) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        //застосовує до кожного зі значень потоку реалізацію
        // IntUnaryOperator і повертає його в результуючий потік
        this.streamIterator = new Iterator<Integer>() {
            private final Iterator<Integer> oldIterator = iterator();

            @Override
            public boolean hasNext() {
                return oldIterator.hasNext();
            }

            @Override
            public Integer next() {
                int value = oldIterator.next();
                return mapper.apply(value);
            }
        };
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        //застосовує до кожного зі значень потоку реалізацію
        // IntToIntStreamFunction,
        // яка на основі кожного зі значення створює новий потік значень,
        // які потім об'єднуються в один результуючий потік
        this.streamIterator = new
                Iterator<Integer>() {
                    private final Iterator<Integer> newIterStream = iterator();
                    private Iterator<Integer> updatedIterator;

                    @Override
                    public boolean hasNext() {
                        while (updatedIterator == null
                               || !updatedIterator.hasNext()) {
                            if (!newIterStream.hasNext()) {
                                return false;
                            }
                            updatedIterator = ((AsIntStream)func
                                    .applyAsIntStream(newIterStream.next()))
                                    .iterator();
                        }
                        return true;
                    }

                    @Override
                    public Integer next() {
                        if (updatedIterator == null) {
                            throw new NoSuchElementException(
                                    "There is no elements!");
                        }
                        return updatedIterator.next();
                    }
                };
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        // виконує згортку значень потоку в ціле число,
        // початкове значення задається identity,
        // функція згортки - в реалізації IntBinaryOperator.
        // Термінальній метод.
        int result = identity;
        for (int value: this) {
            result = op.apply(result, value);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        //повертає потік у вигляді масиву. Термінальній метод.
        List<Integer> copyStream = new ArrayList<>();
        for (int value: this) {
            copyStream.add(value);
        }
        int[] result = new int[copyStream.size()];
        for (int i = 0; i < copyStream.size(); i++) {
            result[i] = copyStream.get(i);
        }
        return result;
    }


    @Override
    public Iterator<Integer> iterator() {
        if (this.streamIterator == null) {
            return elements.iterator();
        }
        return this.streamIterator;
    }
}
