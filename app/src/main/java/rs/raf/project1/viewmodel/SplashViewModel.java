package rs.raf.project1.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(true);

    public SplashViewModel() {
        try{
            Thread.sleep(3000);
            isLoading.setValue(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MutableLiveData<Boolean> isLoading() {
        return isLoading;
    }
}
