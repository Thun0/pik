import { PikFrontPage } from './app.po';

describe('pik-front App', () => {
  let page: PikFrontPage;

  beforeEach(() => {
    page = new PikFrontPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
