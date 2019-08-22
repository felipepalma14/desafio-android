package com.felipe.palma.desafioconcrete.ui.pullrequest;

import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PullRequestPresenterTest {
    private PullRequestContract.View view = mock(PullRequestContract.View.class);
    private IServiceGithub api = mock(IServiceGithub.class);

    private PullRequestPresenter pullRequestPresenter;

    @Before
    public void setup() {
        pullRequestPresenter = new PullRequestPresenter(view, api);
    }

    @Test
    public void whenLoadPullRequestSuccessfully_shouldShowPullRequests() {
        // arrange
        Mockito.doAnswer(invocation -> {
            IServiceGithub.IServiceCallback<List<PullRequestResponse>> mockCallback
                    = (IServiceGithub.IServiceCallback<List<PullRequestResponse>>) invocation.getArguments()[2];
            mockCallback.onSuccess(new ArrayList<>());
            return null;
        }).when(api).getPullRequests(anyString(), anyString(), any());

        // act
        pullRequestPresenter.loadPullRequests("", "");

        // assert
        verify(view).showDialog();
        verify(view).showPullRequests(anyList());
        verify(view).hideDialog();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void whenLoadPullRequestWithError_shouldShowError() {
        // arrange
        Mockito.doAnswer(invocation -> {
            IServiceGithub.IServiceCallback<List<PullRequestResponse>> mockCallback
                    = (IServiceGithub.IServiceCallback<List<PullRequestResponse>>) invocation.getArguments()[2];
            mockCallback.onError("");
            return null;
        }).when(api).getPullRequests(anyString(), anyString(), any());

        // act
        pullRequestPresenter.loadPullRequests("", "");

        // assert
        verify(view).showDialog();
        verify(view).showError(any());
        verify(view).hideDialog();
        verifyNoMoreInteractions(view);
    }
}