package com.ximedes.rb.service;

import akka.dispatch.OnComplete;
import lombok.experimental.UtilityClass;
import rx.Observable;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

/**
 * Created by mawi on 01/11/2015.
 */
@UtilityClass
public class ObservableUtil {

    /**
     * Transform response from Pattern.ask to a RxJava observable
     */
    public static <T> Observable<T> from(final Future<Object> future, final ExecutionContext ec) {
        final Subject<T, T> subj = ReplaySubject.create();

        future.onComplete(new OnComplete<Object>() {
            public void onComplete(final Throwable failure, final Object result) {
                if (failure != null) {
                    //We got a failure, handle it here
                    subj.onError(failure);
                } else {
                    // We got a result, do something with it
                    subj.onNext((T) result);
                    subj.onCompleted();
                }
            }
        }, ec);

        return subj.asObservable();
    }
}

