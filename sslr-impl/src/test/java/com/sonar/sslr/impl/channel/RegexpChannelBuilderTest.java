package com.sonar.sslr.impl.channel;

import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.o2n;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.one2n;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.opt;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.or;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RegexpChannelBuilderTest {

  @Test
  public void testOpt() {
    assertThat(opt("L"), equalTo("L?"));
  }

  @Test
  public void testOne2n() {
    assertThat(one2n("L"), equalTo("L+"));
  }

  @Test
  public void testO2n() {
    assertThat(o2n("L"), equalTo("L*"));
  }
  
  @Test
  public void testOr() {
    assertThat(or("L", "l", "U", "u"), equalTo("(L|l|U|u)"));
  }

}