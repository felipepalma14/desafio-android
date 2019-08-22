package com.felipe.palma.desafioconcrete.ui.pullrequest;

import com.felipe.palma.desafioconcrete.domain.response.PullRequestResponse;
import com.felipe.palma.desafioconcrete.network.IServiceGithub;

import java.util.List;

/**
 * Created by Felipe Palma on 13/07/2019.
 */
public class PullRequestPresenter implements PullRequestContract.Presenter {

    private PullRequestContract.View view;
    private IServiceGithub api;

    public PullRequestPresenter(PullRequestContract.View view, IServiceGithub api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void loadPullRequests(String owner, String repo) {
        this.view.showDialog();
        api.getPullRequests(owner, repo, new IServiceGithub.IServiceCallback<List<PullRequestResponse>>() {
            @Override
            public void onSuccess(List<PullRequestResponse> pullRequestResponse) {
                view.showPullRequests(pullRequestResponse);
                view.hideDialog();
            }

            @Override
            public void onError(String error) {
                view.showError(error);
                view.hideDialog();
            }
        });
    }
}
