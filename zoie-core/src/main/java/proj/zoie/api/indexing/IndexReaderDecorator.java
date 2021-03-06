package proj.zoie.api.indexing;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;

import org.apache.lucene.index.IndexReader;

import proj.zoie.api.ZoieSegmentReader;

/**
 * Decorates a {@link proj.zoie.api.ZoieSegmentReader} to a customized {@link org.apache.lucene.index.IndexReader}.
 */
public interface IndexReaderDecorator<R extends IndexReader> {
  /**
   * Do the decoration
   * @param indexReader source reader
   * @return a decorated reader
   * @throws IOException
   */
  R decorate(ZoieSegmentReader<R> indexReader) throws IOException;

  /**
   * This is called when the actual underlying reader was not changed, but a new reference was created.
   * Generally, the decorated reader should be at least shallow copied due to reuse of the decorated reader.
   * If we dont copy the decorated reader and change the inner reader, it would create a race condition and
   * cause data inconsistency.
   * @param decorated Previously decoreated reader
   * @param copy a new copy of the source reader
   * @return Re-decorated reader
   * @throws IOException
   */
  R redecorate(R decorated, ZoieSegmentReader<R> copy) throws IOException;
}
